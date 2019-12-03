package com.rental.services.models;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.Observer;
import com.rental.domain.interfaces.entities.Company;

import java.util.List;

public class CompanyEntity extends BaseEntity implements Company, Observer {

    //region Private attributes
    private String name;

    private String address;

    private List<String> notifications;
    //endregion

    //region Constructors
    public CompanyEntity() {
    }

    public CompanyEntity(Company company) {
        setId(company.getId());
        name = company.getName();
        address = company.getAddress();
        notifications = company.getNotifications();
    }

    public CompanyEntity(String name, String address) {
        this.name = name;
        this.address = address;
    }
    //endregion

    //region Getters and setters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    public void addNotification(String notification) {
        notifications.add(notification);
    }
    //endregion

    //region Public methods
    public boolean isValid() {
        if ((getName() != null && !getName().isBlank())
                && (getAddress() != null && !getAddress().isBlank())) {
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
        return "Naam: " + getName() + " adres: " + getAddress();
    }
    //endregion
}
