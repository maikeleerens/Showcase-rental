package com.rental.api.viewmodels.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateUserViewModel {
    //region Private attributes
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;
    //endregion

    //region Constructors
    public CreateUserViewModel(String email, String password, String name, String address, String city) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.city = city;
    }
    //endregion

    //region Getters and setters
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }
    //endregion
}
