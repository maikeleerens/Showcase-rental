package com.rental.api.viewmodels.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.interfaces.entities.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CompanyViewModel implements Company {

    //region Private attributes
    @JsonProperty("company_id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("company_notifications")
    private List<String> notifications;
    //endregion

    //region Constructors
    public CompanyViewModel(Company company) {
        id = company.getId();
        name = company.getName();
        address = company.getAddress();
        notifications = company.getNotifications();
    }
    //endregion

    //region Getters and setters

    @Override
    @JsonProperty("company_id")
    public UUID getId() {
        return id;
    }

    @Override
    @JsonProperty("company_id")
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Override
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @Override
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    @JsonProperty("company_notifications")
    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    @JsonProperty("company_notifications")
    public void addNotification(String notification) {
        notifications.add(notification);
    }

    //endregion
}