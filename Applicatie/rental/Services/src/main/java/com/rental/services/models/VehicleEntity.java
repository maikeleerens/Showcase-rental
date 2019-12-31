package com.rental.services.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Vehicle;

import java.math.BigDecimal;

public class VehicleEntity extends BaseEntity implements Vehicle {

    //region Private attributes
    private String licencePlate;

    private String vehicleName;

    private BigDecimal pricePerDay;

    private int mileage;
    //endregion

    //region Constructors
    public VehicleEntity() {
    }

    public VehicleEntity(Vehicle vehicle) {
        super.setId(vehicle.getId());
        this.licencePlate = vehicle.getLicencePlate();
        this.vehicleName = vehicle.getVehicleName();
        this.pricePerDay = vehicle.getPricePerDay();
        this.mileage = vehicle.getMileage();
    }

    public VehicleEntity(String licencePlate, String vehicleName, BigDecimal pricePerDay, int mileage) {
        this.licencePlate = licencePlate;
        this.vehicleName = vehicleName;
        this.pricePerDay = pricePerDay;
        this.mileage = mileage;
    }
    //endregion

    //region Getters and setters
    @Override
    public String getLicencePlate() {
        return licencePlate;
    }

    @Override
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @Override
    public String getVehicleName() {
        return vehicleName;
    }

    @Override
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    @Override
    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    @Override
    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public int getMileage() {
        return mileage;
    }

    @Override
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    //endregion

    //region Public methods
    @JsonIgnore
    public boolean isValid() {
        if ((getLicencePlate() != null && !getLicencePlate().isBlank())
                && (getVehicleName() != null && !getVehicleName().isBlank())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Kenteken: " + getLicencePlate() + " Voertuig: " + getVehicleName();
    }
    //endregion
}
