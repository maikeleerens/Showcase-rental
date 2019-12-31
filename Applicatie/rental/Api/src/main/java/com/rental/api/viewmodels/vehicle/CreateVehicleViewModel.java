package com.rental.api.viewmodels.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CreateVehicleViewModel{

    //region Private attributes
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

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    //endregion
}
