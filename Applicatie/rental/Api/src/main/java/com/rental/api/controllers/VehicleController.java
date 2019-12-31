package com.rental.api.controllers;

import com.rental.api.viewmodels.vehicle.CreateVehicleViewModel;
import com.rental.api.viewmodels.vehicle.UpdateVehicleViewModel;
import com.rental.api.viewmodels.vehicle.VehicleViewModel;
import com.rental.services.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
@Api(tags = {"Vehicle management"})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully executed request"),
        @ApiResponse(code = 400, message = "Failed to execute request"),
        @ApiResponse(code = 401, message = "Unauthorized: Invalid username or password"),
        @ApiResponse(code = 403, message = "Unauthorized: No access to resource"),
        @ApiResponse(code = 404, message = "Resource not found")
})
public class VehicleController {

    //region Private attributes
    private VehicleService _service;
    //endregion

    @Autowired
    public VehicleController(VehicleService service) {
        _service = service;
    }

    @ApiOperation(value = "Gets all vehicles", response = VehicleViewModel.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity getAllVehicles() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(VehicleViewModel.toVehicleViewModels(_service.getAllVehicles()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets a vehicle by id", response = VehicleViewModel.class)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping("/id/{id}")
    public ResponseEntity getVehicleById(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(new VehicleViewModel(_service.getVehicleById(id)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets a vehicle by licence plate", response = VehicleViewModel.class)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping("plate/{licencePlate}")
    public ResponseEntity getVehicleByLicencePlate(@PathVariable String licencePlate) {
        try {
            if (licencePlate == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Licence plate is empty");
            return ResponseEntity.status(HttpStatus.OK).body(new VehicleViewModel(_service.getByLicencePlate(licencePlate)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Creates a new vehicle", response = VehicleViewModel.class)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @PostMapping("/create")
    public ResponseEntity createVehicle(@RequestBody CreateVehicleViewModel vehicle) {
        try {
            var createdVehicle = _service.createVehicle(VehicleViewModel.toVehicleViewModel(vehicle));
            if (createdVehicle == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create vehicle");
            return ResponseEntity.status(HttpStatus.OK).body(createdVehicle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Updates an existing vehicle by id", response = VehicleViewModel.class)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @PutMapping("/edit")
    public ResponseEntity editVehicle(@RequestBody UpdateVehicleViewModel vehicle) {
        try {
            VehicleViewModel updatedVehicle = new VehicleViewModel(_service.updateVehicle(vehicle));
            if (updatedVehicle == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update vehicle");
            return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
