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

    @Column(name = "name", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private String name;
    @Column(name = "address", nullable = false)
    @JsonInclude(Include.NON_DEFAULT)
    private String address;


    public Company() {
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Company(String errorMessage) {
        super.setError(errorMessage);
    }

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
}
