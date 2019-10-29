package com.rental.application.controllers;

import com.rental.domain.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    //Gets all users from database
    //returns code 200: All users where successfully fetched
    //returns code 400: Something went wrong
    @GetMapping("/api/users")
    public ResponseEntity GetAllUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body("GetAllUsers");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //Get a specific user by id
    //returns code 200: User successfully fetched
    //returns code 400: Id is empty
    @GetMapping("/api/users/{id}")
    public ResponseEntity GetUserById(@PathVariable("id") String id) {
        try {
            if ((id == null) || (id.trim().isEmpty()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(id);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //Creates a new user
    //returns code 200: User successfully created
    //returns code 400: User is empty
    @PostMapping("/api/users/create")
    public ResponseEntity CreateUser(@RequestBody User user) {
        try {
            if (user.IsNullOrEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is empty");
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //Updates an existing user
    //returns code 200: User is successfully updated
    //returns code 400: User or id is empty
    @PutMapping("/api/users/edit/{id}")
    public ResponseEntity EditUser(@PathVariable("id") String id, @RequestBody User user) {
        try {
            if ((id == null) || (id.trim().isEmpty()) || (user.IsNullOrEmpty()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id or user is empty");
            return ResponseEntity.status(HttpStatus.OK).body("User with id " + id + " has been updated");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
