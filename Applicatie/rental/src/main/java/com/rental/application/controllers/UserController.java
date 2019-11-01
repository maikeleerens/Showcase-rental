package com.rental.application.controllers;

import com.rental.domain.entities.*;
import com.rental.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity GetAllUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity GetUserById(@PathVariable("id") UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/name{name}")
    public ResponseEntity GetUserById(@PathVariable("name") String name) {
        try {
            if (name == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getUserByName(name));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity CreateUser(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.createUser(user));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity EditUser(@PathVariable("id") UUID id, @RequestBody User user) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, user));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
