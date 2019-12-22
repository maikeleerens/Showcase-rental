package com.rental.services.models;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.Observer;
import com.rental.domain.interfaces.entities.Role;
import com.rental.domain.interfaces.entities.User;

import java.util.List;

public class UserEntity extends BaseEntity implements User, Observer {
    //region Private attributes
    private String email;

    private String password;

    private List<? extends Role> roles;

    private String name;

    private String address;

    private String city;

    private List<String> notifications;
    //endregion

    //region Constructors
    public UserEntity() {
    }

    public UserEntity(User user) {
        setId(user.getId());
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles();
        name = user.getName();
        address = user.getAddress();
        city = user.getCity();
        notifications = user.getNotifications();
    }

    public UserEntity(String email, String password, List<? extends Role> roles, String name, String address, String city) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.name = name;
        this.address = address;
        this.city = city;
    }
    //endregion

    //region Getters and setters

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<? extends Role> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(List<? extends Role> roles) {
        this.roles = roles;
    }

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

    //region Public methods
    public boolean isValid() {
        if ((getName() != null && !getName().isEmpty())
                && (getAddress() != null && !getAddress().isEmpty())
                && (getCity() != null && !getCity().isEmpty())) {
            return true;
        }
        return false;
    }

    @Override
    public void Update(String message) {
        addNotification(message);
    }

    @Override
    public String toString() {
        return "Naam: " + getName() + " adres: " + getAddress() + " stad: " + getCity();
    }
    //endregion
}
