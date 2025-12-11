package com.emerald.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emerald.dto.CredentialsDTO;
import com.emerald.dto.EmployeeDTO;
import com.emerald.dto.LoginDTO;
import com.emerald.dto.UserDetailDTO;
import com.emerald.enums.IDTypeEnum;
import com.emerald.exception.EmployeeNotFoundException;
import com.emerald.model.Employee;
import com.emerald.repository.EmployeeRepository;
import com.emerald.service.EmployeeService;
import com.emerald.service.UsersService;
import com.emerald.util.Tokenizer;

@CrossOrigin
@RestController
@RequestMapping("/sso")
public class SSOController {

    private final UsersService usersService;
    private final EmployeeService employeeService;
    private final Tokenizer tokenMaker;

    @Autowired
    EmployeeRepository employeeRepository;

    SSOController(UsersService usersService, EmployeeService employeeService, Tokenizer tokenMaker){
        this.usersService = usersService;
        this.employeeService = employeeService;
        this.tokenMaker = tokenMaker;
    }

    @GetMapping("/account/{id}")
    public UserDetailDTO viewAccount(@PathVariable int id){
        return usersService.viewUserDetails(id);
    }

    @GetMapping("/account/tokenize/{id}")
    public String tokenize(@PathVariable int id){

        Employee emplogin = employeeRepository.findByUserId(id)
            .orElseThrow(() -> new EmployeeNotFoundException(IDTypeEnum.USER, id));

        
        String token = tokenMaker.createToken(emplogin);

        return token;
    }

     /* Login Endpoint */
    @PostMapping("/login")
    public String login(@RequestBody CredentialsDTO credentials){

        String token = usersService.authenticateUser(credentials);
        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@RequestBody EmployeeDTO newUser){
        usersService.registerUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Account created\"}");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id){

        usersService.deleteEmployee(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Account deleted\"}");
    }

    @PutMapping("/modify/password")
    public ResponseEntity<String> changePassword(@RequestBody LoginDTO updatedLogin) {

        usersService.updatePassword(updatedLogin);

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Password Changed\"}");
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<String> updateDetails(@PathVariable int id, @RequestBody EmployeeDTO updatedEmployee) {
        employeeService.updateEmployeeInfo(id, updatedEmployee);

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Employee details updated\"}");
    }
}
