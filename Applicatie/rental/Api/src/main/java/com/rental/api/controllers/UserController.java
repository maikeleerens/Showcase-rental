package com.rental.api.controllers;

import com.rental.api.viewmodels.user.CreateUserViewModel;
import com.rental.api.viewmodels.user.UpdateUserViewModel;
import com.rental.api.viewmodels.user.UserViewModel;
import com.rental.domain.entities.*;
import com.rental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            var user = service.getUserById(id);
            if (user == null) return ResponseEntity.status(HttpStatus.OK).body("No user found with id: " + id);
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewModel(user));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/name{name}")
    public ResponseEntity getUserByName(@PathVariable("name") String name) {
        try {
            if (name == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is empty");
            var users = service.getUserByName(name);
            if (users == null) return ResponseEntity.status(HttpStatus.OK).body("No users found with name: " + name);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody CreateUserViewModel user) {
        try {
            //if (!user.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not valid");
            var createdUser = service.createUser(user);
            if (createdUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewModel(createdUser));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editUser(@RequestBody UpdateUserViewModel user) {
        try {
            //if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            //if (!user.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not valid");
            var updatedUser = service.updateUser(user);
            if (updatedUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user");
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewModel(updatedUser));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity getUserNotifications(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            if (service.getUserNotifications(id) == null) return ResponseEntity.status(HttpStatus.OK).body("No notifications found");
            return ResponseEntity.status(HttpStatus.OK).body(service.getUserNotifications(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/notifications/export/{id}")
    public ResponseEntity exportNotificationsToFile(@PathVariable UUID id, @RequestBody String filePath) {
        try {
            if (service.exportNotificationsToText(service.getUserNotifications(id), filePath))
                return ResponseEntity.status(HttpStatus.OK).body("File successfully created");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create file");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
