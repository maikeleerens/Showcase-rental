package com.rental.domain.entities.base;

import com.rental.domain.helpers.RandomStringGenerator;

//Base enitity used to give all objects an ID
public class BaseEntity {
    private String id;

    public BaseEntity() {
        this.id = RandomStringGenerator.GenerateRandomAlphanumericString(15);
    }

    public String getId() {
        return id;
    }
}
