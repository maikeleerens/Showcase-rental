package com.rental.services;

import com.rental.domain.interfaces.entities.Vehicle;
import com.rental.infrastructure.repositories.VehicleRepositoryImpl;
import com.rental.services.models.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    VehicleRepositoryImpl repository;

    public List<? extends Vehicle> getAllVehicles() throws Exception {
        return repository.getAll();
    }

    public Vehicle getVehicleById(UUID id) throws Exception {
        return repository.getById(id).orElse(null);
    }

    public Vehicle getByLicencePlate(String licencePlate) throws Exception {
        return repository.getByLicencePlate(licencePlate).orElse(null);
    }

    public Vehicle createVehicle(Vehicle vehicle) throws Exception {
        var vehicleEntity = new VehicleEntity(vehicle);
        if (vehicleEntity.isValid()) {
            return repository.save(vehicleEntity).orElse(null);
        } else {
            return null;
        }
    }

    public Vehicle updateVehicle(Vehicle vehicle) throws Exception {
        var vehicleEntity = new VehicleEntity(vehicle);
        if (vehicleEntity.isValid()) {
            repository.update(vehicleEntity).orElse(null);
            return vehicleEntity;
        } else {
            return null;
        }
    }
}
