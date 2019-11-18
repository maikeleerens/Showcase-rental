package com.rental.services;

import com.rental.domain.entities.Vehicle;
import com.rental.infrastructure.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository repository;

    public List<Vehicle> getAllVehicles() throws Exception {
        var vehicleList = repository.findAll();
        if (vehicleList.size() < 1) {
            return null;
        }
        return repository.findAll();
    }

    public Vehicle getVehicleById(UUID id) throws Exception {
        return repository.findById(id).orElse(null);
    }

    public Vehicle getByLicencePlate(String licencePlate) throws Exception {
        return repository.findByLicencePlate(licencePlate).orElse(null);
    }

    public Vehicle createVehicle(Vehicle vehicle) throws Exception {
        if (vehicle.isValid()) {
            repository.save(vehicle);
            return vehicle;
        } else {
            return null;
        }
    }

    public Vehicle updateVehicle(UUID id, Vehicle vehicle) throws Exception {
        var vehicleToUpdate = getVehicleById(id);
        if (!vehicleToUpdate.isValid())
            return null;

        if (vehicle.isValid()) {
            vehicle.setId(id);
            repository.save(vehicle);
            return vehicle;
        } else {
            return null;
        }
    }
}
