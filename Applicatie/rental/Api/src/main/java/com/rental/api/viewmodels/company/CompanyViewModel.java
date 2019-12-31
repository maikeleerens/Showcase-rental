package com.rental.api.viewmodels.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CompanyViewModel extends BaseEntity implements Company {

    //region Private attributes
    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonIgnore
    private List<String> notifications;
    //endregion

    //region Constructors
    public CompanyViewModel(String name, String address) {
        this.address = address;
        this.name = name;
    }

    public CompanyViewModel(UUID companyId) {
        setId(companyId);
    }

    public CompanyViewModel(Company company) {
        setId(company.getId());
        name = company.getName();
        address = company.getAddress();
        notifications = company.getNotifications();
    }
    //endregion

    //region Getters and setters

    @Override
    @JsonProperty("company_id")
    public UUID getId() {
        return super.getId();
    }

    @Override
    @JsonProperty("company_id")
    public void setId(UUID id) {
        super.setId(id);
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

    //region Public methods
    public static List<CompanyViewModel> toCompanyViewModels(List<? extends Company> companies) {
        List<CompanyViewModel> returnCompanyList = new ArrayList<>();

        for (var company:
                companies) {
            returnCompanyList.add(new CompanyViewModel(company));
        }
        return returnCompanyList;
    }

    public static CompanyViewModel toCompanyViewModel(CreateCompanyViewModel model) {
        return new CompanyViewModel(model.getName(), model.getAddress());
    }
    //endregion
}