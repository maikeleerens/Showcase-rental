package com.rental.domain.interfaces.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface Booking {
    UUID getId();

    void setId(UUID id);

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getEndDate();

    void setEndDate(Date endDate);

    List<? extends Vehicle> getVehicles();

    void setVehicles(List<? extends Vehicle> vehicles);

    User getUser();

    void setUser(User user);

    String getBookingNumber();

    void setBookingNumber(String bookingNumber);

    Company getCompany();

    void setCompany(Company company);

    boolean isReturned();

    void setReturned(boolean returned);

    BigDecimal getTotalPrice();

    void setTotalPrice(BigDecimal totalPrice);
}
