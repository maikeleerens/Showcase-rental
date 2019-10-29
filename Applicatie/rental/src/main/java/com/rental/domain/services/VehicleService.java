package com.rental.domain.services;

import com.rental.domain.entities.Vehicle;
import com.rental.infrastructure.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository repository;

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicleList = repository.findAll();

        return vehicleList;
    }

    public Vehicle CreateVehicle(Vehicle vehicle) {
        repository.save(vehicle);
        return vehicle;
    }
}
