package com.rental.api.controllers;

import com.rental.api.viewmodels.user.CreateUserViewModel;
import com.rental.api.viewmodels.user.UpdateUserViewModel;
import com.rental.api.viewmodels.user.UserViewModel;
import com.rental.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Api(tags = {"User management"})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully executed request"),
        @ApiResponse(code = 400, message = "Failed to execute request"),
        @ApiResponse(code = 401, message = "Unauthorized: Invalid username or password"),
        @ApiResponse(code = 403, message = "Unauthorized: No access to resource"),
        @ApiResponse(code = 404, message = "Resource not found")
})
public class UserController {

    //region Private attributes
    private UserService _service;
    private PasswordEncoder _passwordEncoder;
    //endregion

    @Autowired
    public UserController(UserService service, PasswordEncoder passwordEncoder) {
        _service = service;
        _passwordEncoder = passwordEncoder;
    }

    @ApiOperation(value = "Gets all users", response = UserViewModel.class, responseContainer = "List")
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping
    public ResponseEntity getAllUsers() {
        try {
            var users = _service.getAllUsers();
            if (users == null) return ResponseEntity.status(HttpStatus.OK).body("No users found");
            return ResponseEntity.status(HttpStatus.OK).body(UserViewModel.toUserViewModels(users));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets a user by id", response = UserViewModel.class)
    @PreAuthorize("#id == authentication.principal.id OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/id/{id}")
    public ResponseEntity getUserById(@PathVariable("id") UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            var user = _service.getUserById(id);
            if (user == null) return ResponseEntity.status(HttpStatus.OK).body("No user found with id: " + id);
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewModel(user));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets all users by name", response = UserViewModel.class, responseContainer = "List")
    @PreAuthorize("#name == authentication.principal.name OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/name/{name}")
    public ResponseEntity getUserByName(@PathVariable("name") String name) {
        try {
            if (name == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is empty");
            var users = _service.getUserByName(name);
            if (users == null) return ResponseEntity.status(HttpStatus.OK).body("No users found");
            return ResponseEntity.status(HttpStatus.OK).body(UserViewModel.toUserViewModels(users));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets a user by email", response = UserViewModel.class)
    @PreAuthorize("#email == authentication.principal.email OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable("email") String email) {
        try {
            if (email == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is empty");
            var user = _service.getUserByEmail(email);
            if (user == null) return ResponseEntity.status(HttpStatus.OK).body("No users found with email: " + email);
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewModel(user));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Creates a new user", response = UserViewModel.class)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody CreateUserViewModel user) {
        try {
            var userViewModel = UserViewModel.toUserViewModel(user);
            userViewModel.setPassword(_passwordEncoder.encode(userViewModel.getPassword()));
            var createdUser = _service.createUser(userViewModel);
            if (createdUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewModel(createdUser));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Updates an existing user by id", response = UserViewModel.class)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @PutMapping("/edit/{id}")
    public ResponseEntity editUser(@RequestBody UpdateUserViewModel user) {
        try {
            //if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            //if (!user.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not valid");
            var updatedUser = _service.updateUser(UserViewModel.toUserViewModel(user));
            if (updatedUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user");
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewModel(updatedUser));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets all user notifications by id", response = String.class, responseContainer = "List")
    @PreAuthorize("#id == authentication.principal.id OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/notifications/{id}")
    public ResponseEntity getUserNotifications(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            var notifications = _service.getUserNotifications(id);
            if (notifications == null) return ResponseEntity.status(HttpStatus.OK).body("No notifications found");
            return ResponseEntity.status(HttpStatus.OK).body(notifications);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets all user notifications as a txt file by id", response = String.class)
    @PreAuthorize("#id == authentication.principal.id OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/notifications/export/{id}")
    public ResponseEntity exportNotificationsToFile(@PathVariable UUID id, @RequestBody String filePath) {
        try {
            if (_service.exportNotificationsToText(_service.getUserNotifications(id), filePath))
                return ResponseEntity.status(HttpStatus.OK).body("File successfully created");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create file");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
