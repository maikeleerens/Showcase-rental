package com.rental.application.controllers;

import com.rental.domain.entities.Vehicle;
import com.rental.domain.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Documented;
import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    VehicleService service;

    @GetMapping
    public ResponseEntity GetAllVehicles() {
        try {
            var vehicleList = service.getAllVehicles();
            return vehicleList.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body("No vehicles found") : ResponseEntity.status(HttpStatus.OK).body(vehicleList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity GetVehicleById(@PathVariable UUID id) {
        try {
            if (id == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");

            var vehicle = service.getVehicleById(id);
            if (vehicle.getError())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vehicle.getErrorMessage());

            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("plate/{licenceplate}")
    public ResponseEntity GetVehicleByLicencePlate(@PathVariable String licenceplate) {
        try {
            if (licenceplate == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");

            var vehicle = service.getByLicencePlate(licenceplate);
            if (vehicle.getError())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vehicle.getErrorMessage());

            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity CreateVehicle(@RequestBody Vehicle vehicle) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.CreateVehicle(vehicle));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity EditVehicle(@PathVariable UUID id, @RequestBody Vehicle vehicle) {
        try {
            if ((id == null))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");

            var updatedVehicle = service.UpdateVehicle(id, vehicle);
            if (updatedVehicle.getError())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedVehicle.getErrorMessage());
            return ResponseEntity.status(HttpStatus.OK).body("Vehicle with licence plate " + vehicle.getLicencePlate() + " is successfully updated");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
