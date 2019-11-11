package com.rental.Api.controllers;

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
    public ResponseEntity getAllUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getUserById(@PathVariable("id") UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/name{name}")
    public ResponseEntity getUserByName(@PathVariable("name") String name) {
        try {
            if (name == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getUserByName(name));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            if (!user.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not valid");
            var createdUser = service.createUser(user);
            if (createdUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
            return ResponseEntity.status(HttpStatus.OK).body(createdUser);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editUser(@PathVariable("id") UUID id, @RequestBody User user) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            if (!user.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not valid");
            var updatedUser = service.updateUser(id, user);
            if (updatedUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user");
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity getUserNotifications(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getUserNotifications(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
