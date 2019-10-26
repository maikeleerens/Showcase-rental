package com.rental.domain.entities;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.Observer;

//Huurder class using the Observer interface
public class User extends BaseEntity implements Observer {

    private String name;
    private String address;
    private String city;

    public User(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void Update() {
        System.out.println("HuurderObservertTest");
    }
}
