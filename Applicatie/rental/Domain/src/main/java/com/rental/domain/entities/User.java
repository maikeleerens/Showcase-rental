package com.rental.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.Observer;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Users")
public class User extends BaseEntity implements Observer {

    //region Private attributes
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @ElementCollection
    @Column(name = "notification")
    private List<String> notifications;
    //endregion

    //region Constructors
    public User() {
    }

    public User(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
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

    public void addNotification(String notification) {
        notifications.add(notification);
    }
    //endregion

    //region Public methods
    @JsonIgnore
    public boolean isValid() {
        if ((getName() != null && !getName().isEmpty())
                && (getAddress() != null && !getAddress().isEmpty())
                && (getCity() != null && !getCity().isEmpty())) {
            return true;
        }
        return false;
    }

    @Override
    public void Update(String message) {
        addNotification(message);
    }

    @Override
    public String toString() {
        return "Naam: " + getName() + " adres: " + getAddress() + " stad: " + getCity();
    }
    //endregion
}
