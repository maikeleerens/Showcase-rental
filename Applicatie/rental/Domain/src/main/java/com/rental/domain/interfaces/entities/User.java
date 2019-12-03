package com.rental.domain.interfaces.entities;

import java.util.List;
import java.util.UUID;

public interface User {
    UUID getId();

    void setId(UUID id);

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    String getCity();

    void setCity(String city);

    List<String> getNotifications();

    void addNotification(String notification);
}
