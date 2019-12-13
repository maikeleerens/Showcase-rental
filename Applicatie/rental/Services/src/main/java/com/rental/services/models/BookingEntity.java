package com.rental.services.models;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.Observer;
import com.rental.domain.interfaces.Subject;
import com.rental.domain.interfaces.entities.Booking;
import com.rental.domain.interfaces.entities.Company;
import com.rental.domain.interfaces.entities.User;
import com.rental.domain.interfaces.entities.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingEntity extends BaseEntity implements Booking, Subject {

    //region Private attributes
    private static BigDecimal extraCostPerDay = new BigDecimal(30.00);

    private String bookingNumber;

    private Date startDate;

    private Date endDate;

    private List<? extends Vehicle> vehicles = new ArrayList<>();

    private UserEntity user;

    private CompanyEntity company;

    private boolean isReturned = false;

    private BigDecimal totalPrice;

    private List<Observer> observers = new ArrayList<>();
    //endregion

    //region Constructors
    public BookingEntity(Booking booking) {
        setId(booking.getId());
        bookingNumber = booking.getBookingNumber();
        startDate = booking.getStartDate();
        endDate = booking.getEndDate();
        vehicles = booking.getVehicles();
        user = new UserEntity(booking.getUser());
        company = new CompanyEntity(booking.getCompany());
        totalPrice = booking.getTotalPrice();
    }

    public BookingEntity(Date startDate, Date endDate, List<Vehicle> vehicles, User user, Company company, BigDecimal totalPrice) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicles = vehicles;
        this.user = new UserEntity(user);
        this.company = new CompanyEntity(company);
        this.totalPrice = totalPrice;
    }
    //endregion

    //region Getters and setters

    public static BigDecimal getExtraCostPerDay() {
        return extraCostPerDay;
    }

    public static void setExtraCostPerDay(BigDecimal extraCostPerDay) {
        BookingEntity.extraCostPerDay = extraCostPerDay;
    }

    @Override
    public String getBookingNumber() {
        return bookingNumber;
    }

    @Override
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public List<? extends Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public void setVehicles(List<? extends Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public UserEntity getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = new UserEntity(user);
    }

    @Override
    public CompanyEntity getCompany() {
        return company;
    }

    @Override
    public void setCompany(Company company) {
        this.company = new CompanyEntity(company);
    }

    @Override
    public boolean isReturned() {
        return isReturned;
    }

    @Override
    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    //endregion

    //region Public methods
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
