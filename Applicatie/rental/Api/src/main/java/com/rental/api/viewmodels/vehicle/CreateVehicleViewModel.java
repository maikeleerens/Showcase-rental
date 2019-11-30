package com.rental.api.viewmodels.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.interfaces.entities.Vehicle;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateVehicleViewModel implements Vehicle {

    //region Private attributes
    @JsonIgnore
    private UUID Id;

    @JsonProperty("licence_plate")
    private String licencePlate;

    @JsonProperty("vehicle_name")
    private String vehicleName;

    @JsonProperty("price_per_day")
    private BigDecimal pricePerDay;

    @JsonProperty("mileage")
    private int mileage;

    //endregion

    //region Constructors

    //endregion

    //region Getters setters
    @Override
    @JsonIgnore
    public UUID getId() {
        return Id;
    }

    @Override
    @JsonIgnore
    public void setId(UUID id) {
        Id = id;
    }

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
}
