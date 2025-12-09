/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.emerald.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author protech
 */
@RestController
@RequestMapping("/sso")
public class SSOController {

    /* 
        private final UserRepository userRepo;
        private final EmployeeRepository empRepo

        SSOController(UserRepository userRepository, EmployeeRepository empRepository){
            this.userRepo = userRepository;
            this.empRepo = empRepository
        }
    */
    
    @GetMapping(("/hello"))
    public ResponseEntity<String> greetings(){
        
        

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Hello\"}");
    }

    @GetMapping("/viewaccount" /* /viewaccount/{id} */)
    public ResponseEntity<String> viewAccount(/*@PathVariable Long id */){

        // empRepo.getById();

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"This is your account\"}");
    }

     /* Login Endpoint */
    @PostMapping("/login")
    public ResponseEntity<String> login(/* @RequestBody User user */){

        /* if(userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword())){
        
        Employee currentEmployee = empRepo.getEmployeeByUserId(user.getId())
        String token = Tokenizer(currentEmployee)

        }
        */

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Login Successful\" , \"jwtToken\": \"%s\"}" /* , token */);
    }

    @PostMapping("/createaccount")
    public ResponseEntity<String> createAccount( /* @RequestBody User newUser */){

        //userRepo.save(newUser)

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Account created.\"}");
    }

    @DeleteMapping("/deleteaccount" /* "/deleteaccount/{id}" */)
    public ResponseEntity<String> deleteAccount( /* @RequestBody User user, @PathVariable Long id */){

        /*
        userRepo.deleteById(id)
        */

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Account deleted\"}");
    }

    @PutMapping("/changepassword" /* "/changepassword/{id}" */)
    public ResponseEntity<String> changePassword(/* @RequestBody User updatedUser */){

        /*
        userRepo.findById(id).map(user -> { 
            user.setName(updatedUser.getName());
            user.setPassword(updatedUser.getRole());
            return userRepo.save(employee)
         })
        */

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Password Changed\"}");
    }

}
