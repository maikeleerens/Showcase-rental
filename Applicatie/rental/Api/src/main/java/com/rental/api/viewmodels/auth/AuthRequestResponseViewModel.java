package com.rental.api.viewmodels.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthRequestResponseViewModel {

    //region Private attributes
    @JsonProperty("token")
    private String bearerToken;
    //endregion

    //region Constructors

    public AuthRequestResponseViewModel(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    //endregion

    //region Getters and setters

    @JsonProperty("token")
    public String getBearerToken() {
        return bearerToken;
    }

    //region
}
