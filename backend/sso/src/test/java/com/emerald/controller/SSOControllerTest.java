package com.emerald.controller;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.emerald.dto.CredentialsDTO;
import com.emerald.dto.EmployeeDTO;
import com.emerald.dto.LoginDTO;
import com.emerald.dto.UserDetailDTO;
import com.emerald.exception.EmployeeNotFoundException;
import com.emerald.model.Employee;
import com.emerald.repository.EmployeeRepository;
import com.emerald.service.EmployeeService;
import com.emerald.service.UsersService;
import com.emerald.util.Tokenizer;

class SSOControllerTest {
    @Mock
    private UsersService usersService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private Tokenizer tokenMaker;
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private SSOController ssoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ssoController = new SSOController(usersService, employeeService, tokenMaker);
        ssoController.employeeRepository = employeeRepository;
    }

    @Test
    void testViewAccount() {
        UserDetailDTO userDetail = new UserDetailDTO();
        when(usersService.viewUserDetails(1)).thenReturn(userDetail);
        assertEquals(userDetail, ssoController.viewAccount(1));
    }

    @Test
    void testTokenizeSuccess() {
        Employee emp = new Employee();
        when(employeeRepository.findByUserId(1)).thenReturn(Optional.of(emp));
        when(tokenMaker.createToken(emp)).thenReturn("token123");
        assertEquals("token123", ssoController.tokenize(1));
    }

    @Test
    void testTokenizeEmployeeNotFound() {
        when(employeeRepository.findByUserId(2)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> ssoController.tokenize(2));
    }

    @Test
    void testLogin() {
        CredentialsDTO creds = new CredentialsDTO();
        when(usersService.authenticateUser(creds)).thenReturn("token456");
        assertEquals("token456", ssoController.login(creds));
    }

    @Test
    void testCreateAccount() {
        EmployeeDTO newUser = new EmployeeDTO();
    ResponseEntity<String> response = ssoController.createAccount(newUser);
    verify(usersService).registerUser(newUser);
    assertEquals(201, response.getStatusCode().value());
    assertTrue(response.getBody().contains("Account created"));
    }

    @Test
    void testDeleteAccount() {
        doNothing().when(usersService).deleteEmployee(1);
        ResponseEntity<String> response = ssoController.deleteAccount(1);
    assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().contains("Account deleted"));
    }

    @Test
    void testChangePassword() {
        LoginDTO loginDTO = new LoginDTO();
        doNothing().when(usersService).updatePassword(loginDTO);
        ResponseEntity<String> response = ssoController.changePassword(loginDTO);
    assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().contains("Password Changed"));
    }

    @Test
    void testUpdateDetails() {
        EmployeeDTO updatedEmployee = new EmployeeDTO();
    ResponseEntity<String> response = ssoController.updateDetails(1, updatedEmployee);
    verify(employeeService).updateEmployeeInfo(1, updatedEmployee);
    assertEquals(200, response.getStatusCode().value());
    assertTrue(response.getBody().contains("Employee details updated"));
    }
}
