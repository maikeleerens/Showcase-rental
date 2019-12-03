package com.rental.domain.interfaces.entities;

import java.util.List;
import java.util.UUID;

public interface Company {
    UUID getId();

    void setId(UUID id);

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    List<String> getNotifications();

    void addNotification(String notification);
}
