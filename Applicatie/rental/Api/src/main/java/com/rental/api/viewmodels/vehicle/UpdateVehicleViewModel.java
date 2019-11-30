package com.rental.api.viewmodels.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.interfaces.entities.Vehicle;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateVehicleViewModel implements Vehicle {

    //region Private attributes

    @JsonProperty("vehicle_id")
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
    @JsonProperty("vehicle_id")
    public UUID getId() {
        return Id;
    }

    @Override
    @JsonProperty("vehicle_id")
    public void setId(UUID id) {
        Id = id;
    }

    @Override
    @JsonProperty("licence_plate")
    public String getLicencePlate() {
        return licencePlate;
    }

    @Override
    @JsonProperty("licence_plate")
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @Override
    @JsonProperty("vehicle_name")
    public String getVehicleName() {
        return vehicleName;
    }

    @Override
    @JsonProperty("vehicle_name")
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    @Override
    @JsonProperty("price_per_day")
    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    @Override
    @JsonProperty("price_per_day")
    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    @JsonProperty("mileage")
    public int getMileage() {
        return mileage;
    }

    @Override
    @JsonProperty("mileage")
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    //endregion
}
