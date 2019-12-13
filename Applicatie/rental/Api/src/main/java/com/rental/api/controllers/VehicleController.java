package com.rental.api.controllers;

import com.rental.api.viewmodels.helpers.ViewModelHelper;
import com.rental.api.viewmodels.vehicle.CreateVehicleViewModel;
import com.rental.api.viewmodels.vehicle.UpdateVehicleViewModel;
import com.rental.api.viewmodels.vehicle.VehicleViewModel;
import com.rental.services.VehicleService;
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
            return ResponseEntity.status(HttpStatus.OK).body(ViewModelHelper.toVehicleViewModels(service.getAllVehicles()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getVehicleById(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(new VehicleViewModel(service.getVehicleById(id)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("plate/{licencePlate}")
    public ResponseEntity getVehicleByLicencePlate(@PathVariable String licencePlate) {
        try {
            if (licencePlate == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Licence plate is empty");
            return ResponseEntity.status(HttpStatus.OK).body(new VehicleViewModel(service.getByLicencePlate(licencePlate)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity createVehicle(@RequestBody CreateVehicleViewModel vehicle) {
        try {
            var createdVehicle = service.createVehicle(ViewModelHelper.toVehicleViewModel(vehicle));
            if (createdVehicle == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create vehicle");
            return ResponseEntity.status(HttpStatus.OK).body(createdVehicle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity editVehicle(@RequestBody UpdateVehicleViewModel vehicle) {
        try {
            //if (!vehicle.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle is invalid");
            VehicleViewModel updatedVehicle = new VehicleViewModel(service.updateVehicle(vehicle));
            if (updatedVehicle == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update vehicle");
            return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
