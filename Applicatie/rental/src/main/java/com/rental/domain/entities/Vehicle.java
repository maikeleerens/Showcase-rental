package com.rental.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rental.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Vehicles")
public class Vehicle extends BaseEntity {

    //region Private attributes
    @Column(name = "licence_plate", unique = true, nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private String licencePlate;

    @Column(name = "vehicle_name", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private String vehicleName;

    @Column(name = "price_per_day", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private BigDecimal pricePerDay;

    @Column(name = "mileage", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private int mileage;
    //endregion

    //region Constructors
    public Vehicle() {
    }

    public Vehicle(String licencePlate, String vehicleName, BigDecimal pricePerDay, int mileage) {
        this.licencePlate = licencePlate;
        this.vehicleName = vehicleName;
        this.pricePerDay = pricePerDay;
        this.mileage = mileage;
    }

    public Vehicle(String errorMessage) {
        super.setError(errorMessage);
    }
    //endregion

    //region Getters and setters
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
    //endregion

    //region Public methods
    @JsonIgnore
    public boolean isValid() {
        if (!getError()
                && (getLicencePlate() != null && !getLicencePlate().isEmpty())
                && (getVehicleName() != null && !getVehicleName().isEmpty())) {
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
