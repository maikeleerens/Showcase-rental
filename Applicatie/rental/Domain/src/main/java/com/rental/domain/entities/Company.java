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
@Table(name = "Companies")
public class Company extends BaseEntity implements Observer {

    //region Private attributes
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @ElementCollection
    @Column(name = "notification")
    private List<String> notifications;
    //endregion

    //region Constructors
    public Company() {
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
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

    public void addNotification(String notification) {
        notifications.add(notification);
    }
    //endregion

    //region Public methods
    @JsonIgnore
    public boolean isValid() {
        if ((getName() != null && !getName().isEmpty())
                && (getAddress() != null && !getAddress().isEmpty())) {
            return true;
        }
        return false;
    }

    @Override
    public void Update(String message) {
        addNotification(message);
    }
    //endregion
}
