package com.rental.domain.interfaces.entities;

import java.util.List;
import java.util.UUID;

public interface User {
    UUID getId();

    void setId(UUID id);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    List<? extends Role> getRoles();

    void setRoles(List<? extends Role> roles);

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    String getCity();

    void setCity(String city);

    List<String> getNotifications();

    void addNotification(String notification);
}
