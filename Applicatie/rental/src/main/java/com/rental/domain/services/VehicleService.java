package com.rental.domain.services;

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

    public List<Vehicle> getAllVehicles() {
        return repository.findAll();
    }

    public Vehicle getVehicleById(UUID id) throws NullPointerException {
        return repository.findById(id).orElse(new Vehicle("No vehicle found with id: " + id));
    }

    public Vehicle getByLicencePlate(String licencePlate) throws NullPointerException {
        return repository.findByLicencePlate(licencePlate).orElse(new Vehicle("No vehicle found with licence plate: " + licencePlate));
    }

    public Vehicle CreateVehicle(Vehicle vehicle) throws NullPointerException {
        if (vehicle.isValid()) {
            repository.save(vehicle);
            return vehicle;
        } else {
            return new Vehicle("Invalid vehicle");
        }
    }

    public Vehicle UpdateVehicle(UUID id, Vehicle vehicle) {
        var vehicleToUpdate = getVehicleById(id);
        if (vehicleToUpdate.getError() || !vehicleToUpdate.isValid())
            return vehicleToUpdate;

        if (!vehicle.getError() && vehicle.isValid()) {
            vehicle.setId(id);
            repository.save(vehicle);
            return vehicle;
        } else {
            return new Vehicle("Invalid vehicle");
        }
    }
}
