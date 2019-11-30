package com.rental.api.viewmodels.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.interfaces.entities.Vehicle;

import java.math.BigDecimal;
import java.util.UUID;

public class VehicleViewModel implements Vehicle {

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
    public VehicleViewModel(Vehicle vehicle) {
        this.Id = vehicle.getId();
        this.licencePlate = vehicle.getLicencePlate();
        this.vehicleName = vehicle.getVehicleName();
        this.pricePerDay = vehicle.getPricePerDay();
        this.mileage = vehicle.getMileage();
    }
    //endregion

    //region Getters setters

    @Override
    public UUID getId() {
        return Id;
    }

    @Override
    public void setId(UUID Id) {
        this.Id = Id;
    }

    @Override
    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @Override
    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    @Override
    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    //endregion

    //region Public methods

    public static VehicleViewModel toVehicleViewModel(Vehicle vehicle) {
        return new VehicleViewModel(vehicle);
    }
    //endregion

}
