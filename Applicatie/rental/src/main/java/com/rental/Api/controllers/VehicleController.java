package com.rental.Api.controllers;

import com.rental.domain.entities.Vehicle;
import com.rental.domain.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    VehicleService service;

    @GetMapping
    public ResponseEntity getAllVehicles() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllVehicles());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getVehicleById(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getVehicleById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("plate/{licenceplate}")
    public ResponseEntity getVehicleByLicencePlate(@PathVariable String licenceplate) {
        try {
            if (licenceplate == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Licence plate is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getByLicencePlate(licenceplate));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity createVehicle(@RequestBody Vehicle vehicle) {
        try {
            if (!vehicle.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle is invalid");
            var createdVehicle = service.createVehicle(vehicle);
            if (createdVehicle == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create vehicle");
            return ResponseEntity.status(HttpStatus.OK).body(createdVehicle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editVehicle(@PathVariable UUID id, @RequestBody Vehicle vehicle) {
        try {
            if ((id == null)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            if (!vehicle.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle is invalid");
            var updatedVehicle = service.updateVehicle(id, vehicle);
            if (updatedVehicle == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update vehicle");
            return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
