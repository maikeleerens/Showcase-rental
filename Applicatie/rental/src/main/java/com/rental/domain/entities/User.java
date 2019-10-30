package com.rental.domain.entities;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.Observer;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedAttributeNode;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User extends BaseEntity implements Observer {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    public User() {
    }

    public User(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
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
    public UUID getId() {
        return super.getId();
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
