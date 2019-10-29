package com.rental.domain.entities;

import com.rental.domain.interfaces.Observer;
import com.rental.domain.interfaces.Subject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking implements Subject {

    private String id;
    private String bookingNumber;
    private Date startDate;
    private Date endDate;
    private List<Vehicle> vehicles = new ArrayList<>();
    private User user;
    private boolean isReturned = false;
    private BigDecimal totalPrice;
    private List<Observer> observers = new ArrayList<>();

    public Booking() {
    }

    public Booking(Date startDate, Date endDate, List<Vehicle> vehicles, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicles = vehicles;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    @Override
    public void Attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void Detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void Notify() {
        for (var observer : observers) {
            observer.Update();
        }
    }

    public boolean IsNullOrEmpty() {
        return ((this.startDate == null) || (this.endDate == null))
                || ((this.user == null) || (this.vehicles == null))
                || ((this.vehicles.size() < 1));
    }
}
