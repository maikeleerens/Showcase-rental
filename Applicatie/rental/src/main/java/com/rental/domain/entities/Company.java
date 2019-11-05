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
@Table(name = "Companies")
public class Company extends BaseEntity implements Observer {

    //region Private attributes
    @Column(name = "name", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private String name;

    @Column(name = "address", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private String address;
    //endregion

    //region Constructors
    public Company() {
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Company(String errorMessage) {
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
    //endregion

    //region Public methods
    @JsonIgnore
    public boolean isValid() {
        if (!getError()
                && (getName() != null && !getName().isEmpty())
                && (getAddress() != null && !getAddress().isEmpty())) {
            return true;
        }
        return false;
    }

    @Override
    public void Update() {
        System.out.println("VerhuurderObserverTest");
    }
    //endregion
}
