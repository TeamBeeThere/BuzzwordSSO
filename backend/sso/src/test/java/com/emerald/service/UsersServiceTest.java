package com.emerald.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.emerald.dto.CredentialsDTO;
import com.emerald.dto.EmployeeDTO;
import com.emerald.dto.LoginDTO;
import com.emerald.dto.UserDTO;
import com.emerald.dto.UserDetailDTO;
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

class UsersServiceTest {
    @Mock private EmployeeRepository employeeRepository;
    @Mock private UsersRepository userRepository;
    @Mock private LoginRepository loginRepository;
    @Mock private LocationRepository locationRepository;
    @Mock private DepartmentRepository departmentRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private Tokenizer tokenMaker;

    @InjectMocks private UsersService usersService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void registerUser_success() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setTitle("Engineer");
        dto.setDepartment(1);
        dto.setLocationId(2);
        Users user = new Users("johndoe");
        user.setId(1);
        when(userRepository.saveAndFlush(any(Users.class))).thenReturn(user);
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());
        when(passwordEncoder.encode(anyString())).thenReturn("hashed");
        when(loginRepository.save(any(Login.class))).thenReturn(new Login());
        Users result = usersService.registerUser(dto);
        assertNotNull(result);
        assertEquals("johndoe", result.getUserName());
    }

    @Test
    void authenticateUser_success() {
        CredentialsDTO creds = new CredentialsDTO();
        creds.setUserName("johndoe");
        creds.setPassword("password");
        Users user = new Users("johndoe");
        user.setId(1);
        Login login = new Login(1, "hashed");
        Employee employee = new Employee();
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
        when(loginRepository.findByUserID(anyInt())).thenReturn(Optional.of(login));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(employeeRepository.findByUserId(anyInt())).thenReturn(Optional.of(employee));
        when(tokenMaker.createToken(any(Employee.class))).thenReturn("token");
        String token = usersService.authenticateUser(creds);
        assertEquals("token", token);
    }

    @Test
    void authenticateUser_invalidCredentials() {
        CredentialsDTO creds = new CredentialsDTO();
        creds.setUserName("johndoe");
        creds.setPassword("badpass");
        Users user = new Users("johndoe");
        user.setId(1);
        Login login = new Login(1, "hashed");
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
        when(loginRepository.findByUserID(anyInt())).thenReturn(Optional.of(login));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        assertThrows(SecurityException.class, () -> usersService.authenticateUser(creds));
    }

    @Test
    void viewUserDetails_success() {
        Users user = new Users("johndoe");
        user.setId(1);
        Employee employee = new Employee();
        employee.setDepartment(1);
        employee.setLocation(2);
        Departments dept = new Departments();
        dept.setName("Engineering");
        Location loc = new Location();
        loc.setCountry("USA");
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(employeeRepository.findByUserId(anyInt())).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(dept));
        when(locationRepository.findById(anyInt())).thenReturn(Optional.of(loc));
        UserDetailDTO dto = usersService.viewUserDetails(1);
        assertEquals("Engineering", dto.getDepartment());
    assertEquals("USA", dto.getLocation());
    }

    @Test
    void updateUserDetails_success() {
        UserDTO dto = new UserDTO();
        dto.setUserId(1);
        dto.setUserName("newname");
        Users user = new Users("oldname");
        user.setId(1);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any(Users.class))).thenReturn(user);
        Users updated = usersService.updateUserDetails(dto);
        assertEquals("newname", updated.getUserName());
    }

    @Test
    void updatePassword_success() {
        LoginDTO dto = new LoginDTO();
        dto.setUserId(1);
        dto.setPassword("newpass");
        Login login = new Login(1, "oldhash");
        when(loginRepository.findByUserID(anyInt())).thenReturn(Optional.of(login));
        when(passwordEncoder.encode(anyString())).thenReturn("newhash");
        when(loginRepository.save(any(Login.class))).thenReturn(login);
        assertDoesNotThrow(() -> usersService.updatePassword(dto));
        assertEquals("newhash", login.getPasswordHash());
    }

    @Test
    void deleteEmployee_success() {
        Users user = new Users("johndoe");
        user.setId(1);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(loginRepository.findByUserID(anyInt())).thenReturn(Optional.of(new Login()));
        assertDoesNotThrow(() -> usersService.deleteEmployee(1));
        verify(employeeRepository).deleteByUserId(anyInt());
        verify(loginRepository).delete(any(Login.class));
        verify(userRepository).delete(any(Users.class));
    }

    @Test
    void deleteEmployee_userNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> usersService.deleteEmployee(1));
    }
}
