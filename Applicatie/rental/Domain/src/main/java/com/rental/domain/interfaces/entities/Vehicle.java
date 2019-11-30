package com.rental.domain.interfaces.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.UUID;

public interface Vehicle {
    UUID getId();

    void setId(UUID id);

    String getLicencePlate();

    void setLicencePlate(String licencePlate);

    String getVehicleName();

    void setVehicleName(String vehicleName);

    BigDecimal getPricePerDay();

    void setPricePerDay(BigDecimal pricePerDay);

    int getMileage();

    void setMileage(int mileage);
}
