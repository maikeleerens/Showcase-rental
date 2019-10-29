package com.rental.domain.entities;

import java.math.BigDecimal;

public class Vehicle {

    private String id;
    private String licencePlate;
    private String vehicleName;
    private BigDecimal pricePerDay;
    private int mileage;

    public Vehicle() {
    }

    public Vehicle(String licencePlate, String vehicleName, BigDecimal pricePerDay, int mileage) {
        this.licencePlate = licencePlate;
        this.vehicleName = vehicleName;
        this.pricePerDay = pricePerDay;
        this.mileage = mileage;
    }

    public String getId() {
        return id;
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

    public boolean IsNullOrEmpty()
    {
        return ((this.licencePlate == null) || this.licencePlate.trim().isEmpty())
                || ((this.vehicleName == null) || this.vehicleName.trim().isEmpty())
                || ((this.mileage < 1) || this.pricePerDay.compareTo(BigDecimal.valueOf(1)) < 0);
    }
}
