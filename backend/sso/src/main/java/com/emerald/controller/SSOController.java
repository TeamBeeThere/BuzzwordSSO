/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.emerald.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emerald.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 *
 * @author protech
 */
@RestController
@RequestMapping("/sso")
public class SSOController {

    //Once repository is done
        // private final UserRepository repository;

        // SSOController(UserRepository repository){
        //     this.repository = repository;
        // }
    
    @GetMapping(("/hello"))
    public ResponseEntity<String> greetings(){
        
        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Hello\"}");
    }

    @GetMapping("/viewaccount" /* /viewaccount/{id} */)
    public ResponseEntity<String> viewAccount(/*@PathVariable Long id */){

        // repository.getAccountById

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"This is your account\"}");
    }

    @PostMapping("/createaccount")
    public ResponseEntity<String> createAccount( /* @RequestBody Employee newEmployee */){

        //repository.save(newEmployee)

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Account created.\"}");
    }

    @DeleteMapping("/deleteaccount" /* "/deleteaccount/{id}" */)
    public ResponseEntity<String> deleteAccount( /* @RequestBody Employee updatedEmployee, @PathVariable Long id */){

        /*
        repository.deleteById(id)
        */

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Account deleted\"}");
    }

    @PutMapping("/changepassword")
    public ResponseEntity<String> changePassword(){

        /*
        repository.findById(id).map(employee -> { 
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repository.save(employee)
         })
        */

        return ResponseEntity.status(HttpStatus.OK)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body("{\"message\": \"Password Changed\"}");
    }

}
