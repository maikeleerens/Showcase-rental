package com.rental.domain.entities;

import com.rental.domain.entities.base.BaseEntity;

import java.math.BigDecimal;

public class Vehicle extends BaseEntity {

    private String licencePlate;
    private String vehicleName;
    private BigDecimal pricePerKm;
    private int mileage;

    public Vehicle() {
    }

    public Vehicle(String licencePlate, String vehicleName, BigDecimal pricePerKm, int mileage) {
        this.licencePlate = licencePlate;
        this.vehicleName = vehicleName;
        this.pricePerKm = pricePerKm;
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

    public BigDecimal getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(BigDecimal pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public boolean IsNullOrEmpty()
    {
        return ((this.licencePlate == null) || this.licencePlate.trim().isEmpty())
                || ((this.vehicleName == null) || this.vehicleName.trim().isEmpty())
                || ((this.mileage < 1) || this.pricePerKm.compareTo(BigDecimal.valueOf(1)) < 0);
    }
}
