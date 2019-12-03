package com.rental.infrastructure.datamodels;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Company;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Companies")
public class CompanyDataModel extends BaseEntity implements Company {

    //region Private attributes
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @CollectionTable(name = "company_notifications", joinColumns = @JoinColumn(name = "company_id"))
    @ElementCollection
    @Column(name = "notification")
    private List<String> notifications;
    //endregion

    //region Constructors
    public CompanyDataModel() {
    }

    public CompanyDataModel(Company company) {
        setId(company.getId());
        name = company.getName();
        address = company.getAddress();
        notifications = company.getNotifications();
    }

    public CompanyDataModel(String name, String address) {
        this.name = name;
        this.address = address;
    }
    //endregion

    //region Getters and setters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    public void addNotification(String notification) {
        notifications.add(notification);
    }
    //endregion
}
