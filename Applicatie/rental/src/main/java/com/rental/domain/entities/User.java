package com.rental.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.Observer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends BaseEntity implements Observer {

    //region Private attributes
    @Column(name = "name", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private String name;

    @Column(name = "address", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private String address;

    @Column(name = "city", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private String city;
    //endregion

    //region Constructors
    public User() {
    }

    public User(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public User(String errorMessage) {
        super.setError(errorMessage);
    }
    //endregion

    //region Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    //endregion

    //region Public methods
    @JsonIgnore
    public boolean isValid() {
        if (!getError()
                && (getName() != null && !getName().isEmpty())
                && (getAddress() != null && !getAddress().isEmpty())
                && (getCity() != null && !getCity().isEmpty())) {
            return true;
        }
        return false;
    }

    @Override
    public void Update() {
        System.out.println("HuurderObservertTest");
    }

    @Override
    public String toString() {
        return "Naam: " + getName() + " adres: " + getAddress() + " stad: " + getCity();
    }
    //endregion
}
