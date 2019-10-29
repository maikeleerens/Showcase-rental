package com.rental.application.controllers;

import com.rental.domain.entities.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    //Gets all vehicles
    //returns code 200: All vehicles successfully fetched
    //returns code 400: Something went wrong
    @GetMapping("/api/vehicles")
    public ResponseEntity GetAllVehicles() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body("GetAllVehicles");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //Get a vehicle by id
    //returns code 200: Vehicle successfully fetched
    //returns code 400" Id is empty
    @GetMapping("/api/vehicles/{id}")
    public ResponseEntity GetVehicleById(@PathVariable String id) {
        try {
            if ((id == null) || (id.trim().isEmpty()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body("GetVehicleById");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //Create a new vehicle
    //returns code 200: New vehicle successfully created
    //returns code 400: Vehicle is empty
    @PostMapping("/api/vehicles/create")
    public ResponseEntity CreateVehicle(@RequestBody Vehicle vehicle) {
        try {
            if (vehicle.IsNullOrEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle is empty");
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //Update an existing vehicle
    //returns code 200: Vehicle successfully updated
    //returns code 400: Vehicle or id is empty
    @PutMapping("/api/vehicles/edit/{id}")
    public ResponseEntity EditVehicle(@PathVariable String id, @RequestBody Vehicle vehicle) {
        try {
            if ((id == null) || (id.trim().isEmpty()) || (vehicle.IsNullOrEmpty()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id or vehicle is empty");
            return ResponseEntity.status(HttpStatus.OK).body("Vehicle with id " + id + " is successfully updated");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}