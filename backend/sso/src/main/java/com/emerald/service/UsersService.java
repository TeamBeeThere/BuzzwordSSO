package com.emerald.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerald.dto.CredentialsDTO;
import com.emerald.dto.EmployeeDTO;
import com.emerald.dto.LoginDTO;
import com.emerald.dto.UserDTO;
import com.emerald.dto.UserDetailDTO;
import com.emerald.enums.IDTypeEnum;
import com.emerald.exception.DepartmentNotFoundException;
import com.emerald.exception.EmployeeNotFoundException;
import com.emerald.exception.LocationNotFoundException;
import com.emerald.exception.UserNotFoundException;
import com.emerald.model.Departments;
import com.emerald.model.Employee;
import com.emerald.model.Location;
import com.emerald.model.Login;
import com.emerald.model.Users;
import com.emerald.repository.DepartmentRepository;
import com.emerald.repository.EmployeeRepository;
import com.emerald.repository.LocationRepository;
import com.emerald.repository.LoginRepository;
import com.emerald.repository.UsersRepository;
import com.emerald.util.Tokenizer;
import com.emerald.util.UserUtils;

@Service
public class UsersService {

    // --- Dependencies (Fields) ---
    private final EmployeeRepository employeeRepository;
    private final UsersRepository userRepository;
    private final LoginRepository loginRepository;
    private final LocationRepository locationRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;
    private final Tokenizer tokenMaker;

    // --- Constructor Injection ---
    /**
     * Constructor used by Spring to inject the required repository dependencies.
     */
    public UsersService(
        EmployeeRepository employeeRepository,
        UsersRepository userRepository, 
        LoginRepository loginRepository,
        LocationRepository locationRepository,
        DepartmentRepository departmentRepository,
        PasswordEncoder passwordEncoder,
        Tokenizer tokenMaker
    ) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.locationRepository = locationRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenMaker = tokenMaker;
    }

    // Business logic methods would go here, focusing on User and Login operations...
    public Users registerUser(EmployeeDTO request) {
        // Create new employee
        Employee employee = new Employee(
            request.getFirstName().trim(),
            request.getLastName().trim(),
            request.getTitle().trim(),
            request.getDepartment(),
            UserUtils.generateEmail(request.getFirstName(), request.getLastName()),
            request.getLocationId()
        );

        // Create and store new user
        String userName = UserUtils.generateUserName(employee.getFirstName(), employee.getLastName());
        Users user = userRepository.saveAndFlush(new Users(userName));

        // Store new employee
        employee.setUserId(user.getId());
        employeeRepository.save(employee);

        // Create and store new password
        String password = String.format("%s123!", employee.getLastName());
        String hashedPassword = passwordEncoder.encode(password);
        Login login = new Login(user.getId(), hashedPassword);
        loginRepository.save(login);

        return user;
    }

    public String authenticateUser(CredentialsDTO credentials) throws SecurityException {
        // 1. Find the User by ID
        Users user = userRepository.findByUserName(credentials.getUserName())
            .orElseThrow(() -> new SecurityException("Authentication failed: Invalid credentials."));
        
        // 2. Retrieve the stored login hash
        Login login = loginRepository.findByUserID(user.getId())
            .orElseThrow(() -> new SecurityException("Authentication failed: Missing login record." + user.getId()));
            
        // 3. Verify the password
        if (passwordEncoder.matches(credentials.getPassword(), login.getPasswordHash())) {
            Employee employee = employeeRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(IDTypeEnum.USER, user.getId()));
            return tokenMaker.createToken(employee);
        } else {
            throw new SecurityException("Authentication failed: Invalid credentials.");
        }
    }

    public UserDetailDTO viewUserDetails(int userID) {
        Users user = userRepository.findById(userID)
            .orElseThrow(() -> new UserNotFoundException(userID));
        
        Employee employee = employeeRepository.findByUserId(userID)
            .orElseThrow(() -> new EmployeeNotFoundException(IDTypeEnum.USER, userID));
        
        Departments department = departmentRepository.findById(employee.getDepartment())
            .orElseThrow(() -> new DepartmentNotFoundException(employee.getDepartment()));
        Location location = locationRepository.findById(employee.getLocation())
            .orElseThrow(() -> new LocationNotFoundException(employee.getLocation()));

        return new UserDetailDTO(
            user.getUserName(),
            employee.getFirstName(),
            employee.getLastName(),
            department.getName(),
            employee.getTitle(),
            location.getCountry()
        );
    }

    @Transactional
    public Users updateUserDetails(UserDTO updatedUser) {
        
        // 1. Fetch the existing User record
        Users existingUser = userRepository.findById(updatedUser.getUserId())
            .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + updatedUser.getUserId()));
            
        // Update User fields
        existingUser.setUserName(updatedUser.getUserName());
        return userRepository.save(existingUser);
    }

    @Transactional
    public void updatePassword(LoginDTO request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        Login login = loginRepository.findByUserID(request.getUserId())
            .map(
                existing -> {
                    existing.setPasswordHash(hashedPassword);
                    return existing;
            })
            .orElseGet(() -> {
                Login newLogin = new Login();
                newLogin.setUserID(request.getUserId());
                newLogin.setPasswordHash(hashedPassword);
                return newLogin;
            });
        
        loginRepository.save(login);
    }

    @Transactional
    public void deleteEmployee(int id) {
        
        // 1. Check if user exists
        Optional<Users> userToDelete = userRepository.findById(id);
        if (userToDelete.isEmpty()) {
            throw new NoSuchElementException("User not found with ID: " + id);
        }
        
        // 2. Delete the Employee record (assuming employee ID matches user ID)
        employeeRepository.deleteByUserId(id);

        // 3. Delete the Login record (assuming login ID matches user ID)
        loginRepository.findByUserID(id).ifPresent(loginRepository::delete);

        // 4. Delete the User record (Primary record)
        userRepository.delete(userToDelete.get());
    }
}
