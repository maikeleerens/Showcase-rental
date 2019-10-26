package com.rental.application.controllers;

import com.rental.domain.entities.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/users")
    public String Test()
    {
        return "test";
    }
}
