package com.emerald.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerald.dto.EmployeeDTO;
import com.emerald.dto.LoginDTO;
import com.emerald.dto.UserDTO;
import com.emerald.model.Employee;
import com.emerald.model.Login;
import com.emerald.model.Users;
import com.emerald.repository.EmployeeRepository;
import com.emerald.repository.LoginRepository;
import com.emerald.repository.UsersRepository;
import com.emerald.util.UserUtils;

@Service
public class UsersService {

    // --- Dependencies (Fields) ---
    private final EmployeeRepository employeeRepository;
    private final UsersRepository userRepository;
    private final LoginRepository loginRepository;

    // --- Constructor Injection ---
    /**
     * Constructor used by Spring to inject the required repository dependencies.
     */
    public UsersService(EmployeeRepository employeeRepository, UsersRepository userRepository, LoginRepository loginRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    // Business logic methods would go here, focusing on User and Login operations...
    // TODO: Register New Employee
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
        String password = String.format("$s123!", employee.getLastName());
        Login login = new Login(user.getId(), password);
        loginRepository.save(login);

        return user;
    }

    // TODO: Authenticate User
    public Users authenticateUser(UserDTO passedUser, LoginDTO rawPassword) throws SecurityException {
        

        // 1. Find the User by ID
        Users user = userRepository.findByUserName(passedUser.getUserName())
            .orElseThrow(() -> new SecurityException("Authentication failed: Invalid credentials."));
        
        // 2. Retrieve the stored login hash
        Login login = loginRepository.findById(passedUser.getUserId())
            .orElseThrow(() -> new SecurityException("Authentication failed: Missing login record."));
            
        // 3. Verify the password
        // In a real app: if (passwordEncoder.matches(rawPassword, login.getPasswordHash())) { ... }
        if (("HASHED_" + rawPassword).equals(login.getPasswordHash())) { // Placeholder comparison
            return user;
        } else {
            throw new SecurityException("Authentication failed: Invalid credentials.");
        }
    }

    // TODO: Update User Details
    @Transactional
    public Users updateUserDetails(UserDTO updatedUser) {
        
        // 1. Fetch the existing User record
        Users existingUser = userRepository.findById(updatedUser.getUserId())
            .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + updatedUser.getUserId()));
            
        // Update User fields
        existingUser.setUserName(updatedUser.getUserName());
        return userRepository.save(existingUser);
    }

    // TODO: Delete Employee
    @Transactional
    public void deleteEmployee(int id) {
        
        // 1. Check if user exists
        Optional<Users> userToDelete = userRepository.findById(id);
        if (userToDelete.isEmpty()) {
            throw new NoSuchElementException("User not found with ID: " + id);
        }
        
        // 2. Delete the Employee record (assuming employee ID matches user ID)
        employeeRepository.findByUserId(id).ifPresent(employeeRepository::delete);

        // 3. Delete the Login record (assuming login ID matches user ID)
        loginRepository.findByUserId(id).ifPresent(loginRepository::delete);

        // 4. Delete the User record (Primary record)
        userRepository.delete(userToDelete.get());
    }
}
