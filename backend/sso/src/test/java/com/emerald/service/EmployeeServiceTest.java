package com.emerald.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.emerald.dto.EmployeeDTO;
import com.emerald.model.Employee;
import com.emerald.repository.DepartmentRepository;
import com.emerald.repository.EmployeeRepository;
import com.emerald.repository.LocationRepository;

import jakarta.persistence.EntityNotFoundException;

class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateEmployeeInfo_success() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setLocationId(1);
        dto.setDepartment(2);
        dto.setTitle("Engineer");
        dto.setEmail("john.doe@example.com");

        Employee employee = new Employee();
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
            when(locationRepository.findById(anyInt())).thenReturn(Optional.of(new com.emerald.model.Location()));
            when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(new com.emerald.model.Departments()));

        Employee result = employeeService.updateEmployeeInfo(1, dto);
        assertNotNull(result);
        verify(employeeRepository).save(employee);
    }

    @Test
    void updateEmployeeInfo_employeeNotFound() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        EmployeeDTO dto = new EmployeeDTO();
        assertThrows(EntityNotFoundException.class, () -> employeeService.updateEmployeeInfo(1, dto));
    }

    @Test
    void updateEmployeeInfo_invalidLocation() {
        Employee employee = new Employee();
        EmployeeDTO dto = new EmployeeDTO();
        dto.setLocationId(1);
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        when(locationRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> employeeService.updateEmployeeInfo(1, dto));
    }

    @Test
    void updateEmployeeInfo_invalidDepartment() {
        Employee employee = new Employee();
        EmployeeDTO dto = new EmployeeDTO();
        dto.setLocationId(1);
        dto.setDepartment(2);
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));
        when(locationRepository.findById(anyInt())).thenReturn(Optional.of(new com.emerald.model.Location()));
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> employeeService.updateEmployeeInfo(1, dto));
    }
}
