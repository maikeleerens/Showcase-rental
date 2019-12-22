package com.rental.api.viewmodels.auth;

public class AuthRequestResponseViewModel {

    //region Private attributes
    private String bearerToken;
    //endregion

    //region Constructors

    public AuthRequestResponseViewModel(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    //endregion

    //region Getters and setters

    public String getBearerToken() {
        return bearerToken;
    }

    //region
}
