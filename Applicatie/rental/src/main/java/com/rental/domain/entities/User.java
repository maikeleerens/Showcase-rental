package com.rental.domain.entities;

import com.rental.domain.interfaces.Observer;

//Huurder class using the Observer interface
public class User implements Observer {

    private String id;
    private String name;
    private String address;
    private String city;

    public User() {
    }

    public User(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void Update() {
        System.out.println("HuurderObservertTest");
    }

    public boolean IsNullOrEmpty() {
        return ((this.address == null) || this.address.trim().isEmpty())
                || ((this.name == null) || this.name.trim().isEmpty())
                || ((this.city == null) || this.city.trim().isEmpty());
    }
}
