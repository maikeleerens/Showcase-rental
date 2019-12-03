package com.rental.infrastructure.datamodels;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Vehicle;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Vehicles")
public class VehicleDataModel extends BaseEntity implements Vehicle {

    //region Private attributes
    @Column(name = "licence_plate", unique = true, nullable = false)
    private String licencePlate;

    @Column(name = "vehicle_name", nullable = false)
    private String vehicleName;

    @Column(name = "price_per_day", nullable = false)
    private BigDecimal pricePerDay;

    @Column(name = "mileage", nullable = false)
    private int mileage;
    //endregion

    //region Constructors
    public VehicleDataModel() {
    }

    public VehicleDataModel(Vehicle vehicle) {
        setId(vehicle.getId());
        this.licencePlate = vehicle.getLicencePlate();
        this.vehicleName = vehicle.getVehicleName();
        this.pricePerDay = vehicle.getPricePerDay();
        this.mileage = vehicle.getMileage();
    }

    public VehicleDataModel(String licencePlate, String vehicleName, BigDecimal pricePerDay, int mileage) {
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

    //endregion
}
