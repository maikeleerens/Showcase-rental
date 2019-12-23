package com.rental.api.viewmodels.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.api.viewmodels.role.RoleViewModel;
import com.rental.domain.interfaces.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateUserViewModel {
    //region Private attributes
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("roles")
    private List<String> roleIds;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;
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

    @JsonProperty("roles")
    public List<String> getRoleIds() {
        return roleIds;
    }

    @JsonProperty("roles")
    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
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

    public CreateUserViewModel(String email, String password, List<String> roles, String name, String address, String city) {
        this.email = email;
        this.password = password;
        this.roleIds = roles;
        this.name = name;
        this.address = address;
        this.city = city;
    }

}
