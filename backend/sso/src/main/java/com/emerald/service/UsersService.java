package com.emerald.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emerald.repository.EmployeeRepository;
import com.emerald.repository.LoginRepository;
import com.emerald.repository.UsersRepository;

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
    @Autowired
    public UsersService(EmployeeRepository employeeRepository, UsersRepository userRepository, LoginRepository loginRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    // Business logic methods would go here, focusing on User and Login operations...
}