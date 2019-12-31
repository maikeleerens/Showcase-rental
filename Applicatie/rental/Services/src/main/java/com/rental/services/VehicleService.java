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

    //region Private attributes
    private VehicleRepositoryImpl _repository;
    //endregion

    @Autowired
    public VehicleService(VehicleRepositoryImpl repository) {
        _repository = repository;
    }

    public List<? extends Vehicle> getAllVehicles() throws Exception {
        return _repository.getAll();
    }

    public Vehicle getVehicleById(UUID id) throws Exception {
        return _repository.getById(id).orElse(null);
    }

    public Vehicle getByLicencePlate(String licencePlate) throws Exception {
        return _repository.getByLicencePlate(licencePlate).orElse(null);
    }

    public Vehicle createVehicle(Vehicle vehicle) throws Exception {
        var vehicleEntity = new VehicleEntity(vehicle);
        if (vehicleEntity.isValid()) {
            return _repository.save(vehicleEntity).orElse(null);
        } else {
            return null;
        }
    }

    public Vehicle updateVehicle(Vehicle vehicle) throws Exception {
        var vehicleEntity = new VehicleEntity(vehicle);
        if (vehicleEntity.isValid()) {
            _repository.update(vehicleEntity).orElse(null);
            return vehicleEntity;
        } else {
            return null;
        }
    }
}
