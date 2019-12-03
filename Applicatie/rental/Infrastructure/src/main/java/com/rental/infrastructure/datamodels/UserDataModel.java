package com.rental.infrastructure.datamodels;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class UserDataModel extends BaseEntity implements User {
    //region Private attributes
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @CollectionTable(name = "user_notifications", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection
    @Column(name = "notification")
    private List<String> notifications;
    //endregion

    //region Constructors
    public UserDataModel() {
    }

    public UserDataModel(User user) {
        setId(user.getId());
        name = user.getName();
        address = user.getAddress();
        city = user.getCity();
        notifications = user.getNotifications();
    }

    public UserDataModel(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
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
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
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
