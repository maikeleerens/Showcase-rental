package com.rental.domain.interfaces.entities;

import java.util.UUID;

public interface Role {

    UUID getId();

    void setId(UUID id);

    String getName();

    void setName(String name);
}
