package com.emerald.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerald.repository.EmployeeRepository;
import com.emerald.repository.LoginRepository;
import com.emerald.repository.UsersRepository;
import com.emerald.model.Users;
import com.emerald.model.Login;
import com.emerald.model.Employee;

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

    // TODO: Authenticate User
    public Users authenticateUser(Integer userId, String rawPassword, String userName) throws SecurityException {
        
        // 1. Find the User by ID
        Users user = userRepository.findByUserName(userName)
            .orElseThrow(() -> new SecurityException("Authentication failed: Invalid credentials."));
        
        // 2. Retrieve the stored login hash
        Login login = loginRepository.findById(userId)
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
    public Employee updateUserDetails(Integer userId, Users updatedUser, Employee updatedEmployee) {
        
        // 1. Fetch the existing User record
        Users existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
            
        // Update User fields
        existingUser.setUserName(updatedUser.getUserName());
        userRepository.save(existingUser);

        // 2. Fetch the existing Employee record (assuming employee ID matches user ID for simplicity)
        Employee existingEmployee = employeeRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("Employee profile not found for user ID: " + userId));

        // Update Employee fields
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setTitle(updatedEmployee.getTitle());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setLocation(updatedEmployee.getLocation());
        
        return employeeRepository.save(existingEmployee);
    }

    // TODO: Delete Employee
    @Transactional
    public void deleteEmployee(Integer userId) {
        
        // 1. Check if user exists
        Optional<Users> userToDelete = userRepository.findById(userId);
        if (userToDelete.isEmpty()) {
            throw new NoSuchElementException("User not found with ID: " + userId);
        }
        
        // 2. Delete the Employee record (assuming employee ID matches user ID)
        employeeRepository.findById(userId).ifPresent(employeeRepository::delete);

        // 3. Delete the Login record (assuming login ID matches user ID)
        loginRepository.findById(userId).ifPresent(loginRepository::delete);

        // 4. Delete the User record (Primary record)
        userRepository.delete(userToDelete.get());
    }
}
