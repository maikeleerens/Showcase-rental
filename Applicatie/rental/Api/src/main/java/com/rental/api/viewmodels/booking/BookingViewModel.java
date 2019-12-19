package com.rental.api.viewmodels.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.api.viewmodels.company.CompanyViewModel;
import com.rental.api.viewmodels.helpers.ViewModelHelper;
import com.rental.api.viewmodels.user.UserViewModel;
import com.rental.api.viewmodels.vehicle.VehicleViewModel;
import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Booking;
import com.rental.domain.interfaces.entities.Company;
import com.rental.domain.interfaces.entities.User;
import com.rental.domain.interfaces.entities.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BookingViewModel extends BaseEntity implements Booking {

    //region Private attributes
    @JsonProperty("booking_number")
    private String bookingNumber;

    @JsonProperty("start_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @JsonProperty("end_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @JsonProperty("vehicles")
    private List<VehicleViewModel> vehicles = new ArrayList<>();

    @JsonProperty("user")
    private UserViewModel user;

    @JsonProperty("company")
    private CompanyViewModel company;

    @JsonProperty("is_returned")
    private boolean isReturned = false;

    @JsonProperty("total_price")
    private BigDecimal totalPrice;
    //endregion

    //region Constructors


    public BookingViewModel(String bookingNumber, Date startDate, Date endDate, List<VehicleViewModel> vehicles, UserViewModel user, CompanyViewModel company) {
        this.bookingNumber = bookingNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicles = vehicles;
        this.user = user;
        this.company = company;
    }

    public BookingViewModel(UUID id, Date endDate, List<VehicleViewModel> vehicles) {
        setId(id);
        this.endDate = endDate;
        this.vehicles = vehicles;
    }

    public BookingViewModel(Booking booking) {
        setId(booking.getId());
        bookingNumber = booking.getBookingNumber();
        startDate = booking.getStartDate();
        endDate = booking.getEndDate();
        vehicles = ViewModelHelper.toVehicleViewModels(booking.getVehicles());
        user = new UserViewModel(booking.getUser());
        company = new CompanyViewModel(booking.getCompany());
        isReturned = booking.isReturned();
        totalPrice = booking.getTotalPrice();
    }
    //endregion

    //region Getters and setters
    @Override
    @JsonProperty("booking_id")
    public UUID getId() {
        return super.getId();
    }

    @Override
    @JsonProperty("booking_id")
    public void setId(UUID id) {
        super.setId(id);
    }

    @Override
    @JsonProperty("booking_number")
    public String getBookingNumber() {
        return bookingNumber;
    }

    @Override
    @JsonProperty("booking_number")
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    @Override
    @JsonProperty("start_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date getStartDate() {
        return startDate;
    }

    @Override
    @JsonProperty("start_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    @JsonProperty("end_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date getEndDate() {
        return endDate;
    }

    @Override
    @JsonProperty("end_Date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    @JsonProperty("vehicles")
    public List<? extends Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    @JsonProperty("vehicles")
    public void setVehicles(List<? extends Vehicle> vehicles) {
        this.vehicles = ViewModelHelper.toVehicleViewModels(vehicles);
    }

    @Override
    @JsonProperty("user")
    public UserViewModel getUser() {
        return user;
    }

    @Override
    @JsonProperty("user")
    public void setUser(User user) {
        this.user = new UserViewModel(user);
    }

    @Override
    @JsonProperty("company")
    public CompanyViewModel getCompany() {
        return company;
    }

    @Override
    @JsonProperty("company")
    public void setCompany(Company company) {
        this.company = new CompanyViewModel(company);
    }

    @Override
    @JsonProperty("is_returned")
    public boolean isReturned() {
        return isReturned;
    }

    @Override
    @JsonProperty("is_returned")
    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    @JsonProperty("total_price")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    @JsonProperty("total_price")
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    //endregion
}
