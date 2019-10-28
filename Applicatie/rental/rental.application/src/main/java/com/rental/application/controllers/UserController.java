package com.rental.application.controllers;

import com.rental.domain.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/users")
    public ResponseEntity GetAllUsers()
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body("GetAllUsers");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("users/{id}")
    public ResponseEntity GetUserById(@PathVariable("id") String id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(id);
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/users/create")
    public ResponseEntity CreateUser(@RequestBody User user)
    {
        try
        {
            if (user.IsNullOrEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is empty");

            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/users/edit/{id}")
    public ResponseEntity EditUser(@PathVariable("id") String id, User user)
    {
        try
        {
            if ((id == null) || (id.trim().isEmpty()) || (user.IsNullOrEmpty()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id or user is empty");
            return ResponseEntity.status(HttpStatus.OK).body("User with id " + id + " has been updated");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }


    }
}
