package com.rental.domain.services;

import com.rental.domain.entities.Vehicle;
import com.rental.infrastructure.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository repository;

    public List<Vehicle> getAllVehicles() throws Exception {
        var vehicleList = repository.findAll();
        if (vehicleList.size() < 1) {
            return Arrays.asList(new Vehicle("No vehicles found"));
        }
        return repository.findAll();
    }

    public Vehicle getVehicleById(UUID id) throws Exception {
        return repository.findById(id).orElse(new Vehicle("No vehicle found with id: " + id));
    }

    public Vehicle getByLicencePlate(String licencePlate) throws Exception {
        return repository.findByLicencePlate(licencePlate).orElse(new Vehicle("No vehicle found with licence plate: " + licencePlate));
    }

    public Vehicle createVehicle(Vehicle vehicle) throws Exception {
        if (vehicle.isValid()) {
            repository.save(vehicle);
            return vehicle;
        } else {
            return new Vehicle("Invalid vehicle");
        }
    }

    public Vehicle updateVehicle(UUID id, Vehicle vehicle) throws Exception {
        var vehicleToUpdate = getVehicleById(id);
        if (!vehicleToUpdate.isValid())
            return new Vehicle("Vehicle to update is not valid");

        if (vehicle.isValid()) {
            vehicle.setId(id);
            repository.save(vehicle);
            return vehicle;
        } else {
            return new Vehicle("Invalid vehicle");
        }
    }
}
