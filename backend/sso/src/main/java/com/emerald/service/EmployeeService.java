package com.emerald.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emerald.dto.EmployeeDTO;
import com.emerald.model.Employee;
import com.emerald.repository.DepartmentRepository;
import com.emerald.repository.EmployeeRepository;
import com.emerald.repository.LocationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

    // --- Dependencies (Fields) ---
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final LocationRepository locationRepository;

    // --- Constructor Injection ---
    /**
     * Constructor used by Spring to inject the required repository dependencies.
     */
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, LocationRepository locationRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.locationRepository = locationRepository;
    }

    public Employee updateEmployeeInfo(int id, EmployeeDTO request) {
        // Fetch existing employee
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found!"));
        
        
        // location must exist
        locationRepository.findById(request.getLocationId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid Location" + request.getLocationId()));
        // department must exist
        departmentRepository.findById(request.getDepartment())
            .orElseThrow(() -> new IllegalArgumentException("Invalid Department"));

        employee.setFirstName(request.getFirstName().trim());
        employee.setLastName(request.getLastName().trim());
        employee.setLocation(request.getLocationId());
        employee.setDepartment(request.getDepartment());
        employee.setTitle(request.getTitle().trim());
        employee.setEmail(request.getEmail().trim());

        return employeeRepository.save(employee);
    }
}