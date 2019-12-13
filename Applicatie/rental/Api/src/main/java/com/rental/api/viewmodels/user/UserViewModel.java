package com.rental.api.viewmodels.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.interfaces.entities.User;

import java.util.List;
import java.util.UUID;

public class UserViewModel implements User {

    //region Private attributes
    @JsonProperty("user_id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("user_notifications")
    private List<String> notifications;
    //endregion

    //region Constructors
    public UserViewModel(UUID userId) {
        id = userId;
    }

    public UserViewModel(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public UserViewModel(User user) {
        id = user.getId();
        name = user.getName();
        address = user.getAddress();
        city = user.getCity();
        notifications = user.getNotifications();
    }
    //endregion

    //region Getters and setters

    @Override
    @JsonProperty("user_id")
    public UUID getId() {
        return id;
    }

    @Override
    @JsonProperty("user_id")
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
    @JsonProperty("user_notifications")
    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    @JsonProperty("user_notifications")
    public void addNotification(String notification) {
        notifications.add(notification);
    }

    //endregion
}