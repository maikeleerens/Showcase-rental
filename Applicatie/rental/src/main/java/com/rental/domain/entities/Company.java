package com.rental.domain.entities;

import com.rental.domain.interfaces.Observer;

public class Company implements Observer {

    private String id;
    private String name;

    public Company() {
    }

    public String getId() {
        return id;
    }

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void Update() {
        System.out.println("VerhuurderObserverTest");
    }
}
