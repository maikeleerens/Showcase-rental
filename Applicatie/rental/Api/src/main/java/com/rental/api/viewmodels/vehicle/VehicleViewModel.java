package com.rental.api.viewmodels.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VehicleViewModel extends BaseEntity implements Vehicle {

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
    public VehicleViewModel(UUID vehicleId) {
        setId(vehicleId);
    }

    public VehicleViewModel(String licencePlate, String vehicleName, BigDecimal pricePerDay, int mileage) {
        this.licencePlate = licencePlate;
        this.vehicleName = vehicleName;
        this.pricePerDay = pricePerDay;
        this.mileage = mileage;
    }

    public VehicleViewModel(Vehicle vehicle) {
        setId(vehicle.getId());
        this.licencePlate = vehicle.getLicencePlate();
        this.vehicleName = vehicle.getVehicleName();
        this.pricePerDay = vehicle.getPricePerDay();
        this.mileage = vehicle.getMileage();
    }
    //endregion

    //region Getters setters
    @Override
    @JsonProperty("vehicle_id")
    public UUID getId() {
        return super.getId();
    }

    @Override
    @JsonProperty("vehicle_id")
    public void setId(UUID Id) {
        super.setId(Id);
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

    //region Public methods
    public static List<VehicleViewModel> toVehicleViewModels(List<? extends Vehicle> vehicles) {
        List<VehicleViewModel> returnVehicleList = new ArrayList<>();

        for (var vehicle:
                vehicles) {
            returnVehicleList.add(new VehicleViewModel(vehicle));
        }
        return returnVehicleList;
    }

    public static VehicleViewModel toVehicleViewModel(CreateVehicleViewModel model) {
        return new VehicleViewModel(model.getLicencePlate(), model.getVehicleName(), model.getPricePerDay(), model.getMileage());
    }
    //endregion
}
