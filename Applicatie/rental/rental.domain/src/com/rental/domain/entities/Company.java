package com.rental.domain.entities;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.Observer;

public class Company extends BaseEntity implements Observer {

    private String name;

    public Company(String name) {
        this.name = name;
    }

    @Override
    public void Update() {
        System.out.println("VerhuurderObserverTest");
    }
}
