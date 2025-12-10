package com.emerald.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emerald.dto.EmployeeDTO;
import com.emerald.dto.LoginDTO;
import com.emerald.dto.UserDTO;
import com.emerald.dto.UserDetailDTO;
import com.emerald.service.EmployeeService;
import com.emerald.service.UsersService;

@RestController
@RequestMapping("/sso")
public class SSOController {

        private final UsersService usersService;
        private final EmployeeService employeeService;

        SSOController(UsersService usersService, EmployeeService employeeService){
            this.usersService = usersService;
            this.employeeService = employeeService;
        }
    
    @GetMapping("/hello")
    public ResponseEntity<String> greetings(){
        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Hello\"}");
    }

    @GetMapping("/viewaccount/{id}")
    public UserDetailDTO viewAccount(@PathVariable int id){
        UserDetailDTO user = usersService.viewUserDetails(id);

        return user;
    }

     /* Login Endpoint */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user, LoginDTO login){

        usersService.authenticateUser(user, login);

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Login Successful\" , \"jwtToken\": \"%s\"}" /* , token */);
    }

    @PostMapping("/createaccount")
    public ResponseEntity<String> createAccount(@RequestBody EmployeeDTO newUser){

        usersService.registerUser(newUser);

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Account created.\"}");
    }

    @DeleteMapping("/deleteaccount")
    public ResponseEntity<String> deleteAccount(@PathVariable int id){

        usersService.deleteEmployee(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Account deleted\"}");
    }

    @PutMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody UserDTO updatedUser){

        usersService.updateUserDetails(updatedUser);

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Password Changed\"}");
    }

}
