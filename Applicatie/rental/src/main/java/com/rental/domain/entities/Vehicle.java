package com.rental.domain.entities;

import com.rental.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Vehicles")
public class Vehicle extends BaseEntity {

    @Column(name = "LicentePlate", unique = true, nullable = false)
    private String licencePlate;

    @Column(name = "VehicleName", nullable = false)
    private String vehicleName;

    @Column(name = "PricePerDay", nullable = false)
    private BigDecimal pricePerDay;

    @Column(name = "Milage", nullable = false)
    private int mileage;

    public Vehicle() {
    }

    public Vehicle(String licencePlate, String vehicleName, BigDecimal pricePerDay, int mileage) {
        this.licencePlate = licencePlate;
        this.vehicleName = vehicleName;
        this.pricePerDay = pricePerDay;
        this.mileage = mileage;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerKm(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    public boolean IsNullOrEmpty() {
        return ((this.licencePlate == null) || this.licencePlate.trim().isEmpty())
                || ((this.vehicleName == null) || this.vehicleName.trim().isEmpty())
                || ((this.mileage < 1) || this.pricePerDay.compareTo(BigDecimal.valueOf(1)) < 0);
    }

    @Override
    public String toString() {
        return "Kenteken: " + getLicencePlate() + " Voertuig: " + getVehicleName();
    }
}
