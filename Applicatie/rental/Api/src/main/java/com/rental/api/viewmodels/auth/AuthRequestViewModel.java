package com.rental.api.viewmodels.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthRequestViewModel {

    //region Private attributes
    @JsonProperty("email")
    private String username;

    @JsonProperty("password")
    private String password;
    //endregion

    //region Constructors
    public AuthRequestViewModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthRequestViewModel() {

    }
    //endregion

    //region Getters and setters

    @JsonProperty("email")
    public String getUsername() {
        return username;
    }

    @JsonProperty("email")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    //endregion
}
