package com.rental.infrastructure.repositories.interfaces;

import com.rental.domain.interfaces.entities.Vehicle;
import com.rental.infrastructure.repositories.interfaces.base.BaseRepository;

import java.util.Optional;

public interface VehicleRepository extends BaseRepository<Vehicle> {
    Optional<Vehicle> getByLicencePlate(String licencePlate);
}
