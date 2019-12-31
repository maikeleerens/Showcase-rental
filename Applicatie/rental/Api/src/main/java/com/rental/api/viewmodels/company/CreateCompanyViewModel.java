package com.rental.api.viewmodels.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCompanyViewModel{

    //region Private attributes
    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;
    //endregion

    //region Constructors
    public CreateCompanyViewModel() {

    }
    //endregion

    //region Getters and setters

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

    //endregion
}
