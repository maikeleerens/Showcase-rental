package com.rental.api.viewmodels.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.interfaces.entities.User;

import java.util.List;
import java.util.UUID;

public class CreateUserViewModel implements User {
    //region Private attributes
    @JsonIgnore
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonIgnore
    private List<String> notifications;
    //endregion

    //region Getters and setters

    @Override
    @JsonIgnore
    public UUID getId() {
        return id;
    }

    @Override
    @JsonIgnore
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
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @Override
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    @JsonIgnore
    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    @JsonIgnore
    public void addNotification(String notification) {
        notifications.add(notification);
    }

    //endregion
}
