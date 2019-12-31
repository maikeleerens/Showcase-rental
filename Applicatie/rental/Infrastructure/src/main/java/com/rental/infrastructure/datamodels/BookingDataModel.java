package com.rental.infrastructure.datamodels;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Booking;
import com.rental.domain.interfaces.entities.Company;
import com.rental.domain.interfaces.entities.User;
import com.rental.domain.interfaces.entities.Vehicle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Bookings")
public class BookingDataModel extends BaseEntity implements Booking {

    //region Private attributes
    @Column(name = "booking_number", unique = true, nullable = false)
    private String bookingNumber;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @ManyToMany()
    @CollectionTable(name = "bookings_vehicles", joinColumns = @JoinColumn(name = "booking_id"))
    @ElementCollection(targetClass = VehicleDataModel.class)
    private List<? extends Vehicle> vehicles = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserDataModel user;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private CompanyDataModel company;

    @Column(name = "returned", nullable = false)
    private boolean isReturned = false;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
    //endregion

    //region Constructors
    public BookingDataModel() {
    }

    public BookingDataModel(Booking booking) {
        setId(booking.getId());
        bookingNumber = booking.getBookingNumber();
        startDate = booking.getStartDate();
        endDate = booking.getEndDate();
        vehicles = booking.getVehicles();
        user = new UserDataModel(booking.getUser());
        company = new CompanyDataModel(booking.getCompany());
        totalPrice = booking.getTotalPrice();
    }

    public BookingDataModel(Date startDate, Date endDate, List<Vehicle> vehicles, User user, Company company, BigDecimal totalPrice) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicles = vehicles;
        this.user = new UserDataModel(user);
        this.company = new CompanyDataModel(company);
        this.totalPrice = totalPrice;
    }
    //endregion

    //region Getters and setters
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
    public UserDataModel getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = new UserDataModel(user);
    }

    @Override
    public CompanyDataModel getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = new CompanyDataModel(company);
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
    //endregion
}
