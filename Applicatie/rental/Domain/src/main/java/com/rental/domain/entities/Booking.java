package com.rental.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.Observer;
import com.rental.domain.interfaces.Subject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Bookings")
public class Booking extends BaseEntity implements Subject {

    //region Private attributes
    @JsonIgnore
    @Transient
    private static BigDecimal extraCostPerDay = new BigDecimal(30.00);

    @Column(name = "booking_number", unique = true, nullable = false)
    private String bookingNumber;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @ManyToMany()
    private List<Vehicle> vehicles = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "returned", nullable = false)
    private boolean isReturned = false;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Transient
    @JsonIgnore
    private List<Observer> observers = new ArrayList<>();
    //endregion

    //region Constructors
    public Booking() {
    }

    public Booking(Date startDate, Date endDate, List<Vehicle> vehicles, User user, Company company) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicles = vehicles;
        this.user = user;
        this.company = company;
    }
    //endregion

    //region Getters and setters
    public BigDecimal getExtraCostPerDay() {
        return extraCostPerDay;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    //endregion

    //region Public methods
    @JsonIgnore
    public boolean isValid() {
        if ((getBookingNumber() != null && !getBookingNumber().isEmpty())
                && (getStartDate() != null) && (getEndDate() != null)
                && (getUser() != null) && (!getVehicles().isEmpty())
                && (getCompany() != null)) {
            return true;
        }
        return false;
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
        setTotalPrice(getTotalPrice().add(getExtraCostPerDay()));
        for (var observer : observers) {
            observer.Update("Boeking " + getBookingNumber() + " met einddatum " + getEndDate() + " is nog niet teruggebracht. Extra kosten bedragen €" + getExtraCostPerDay() + " per dag. Nieuwe totaalprijs: €" + getTotalPrice());
        }
    }

    @Override
    public String toString() {
        return "Boeking: " + getBookingNumber() + " startdatum: " + getStartDate() + " einddatum: " + getEndDate() + " totaalprijs: €" + getTotalPrice();
    }
    //endregion
}