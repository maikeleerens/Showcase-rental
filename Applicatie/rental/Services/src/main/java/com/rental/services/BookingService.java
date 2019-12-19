package com.rental.services;

import com.rental.domain.interfaces.entities.Booking;
import com.rental.domain.interfaces.entities.Vehicle;
import com.rental.infrastructure.repositories.BookingRepositoryImpl;
import com.rental.services.models.BookingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {

    @Autowired
    BookingRepositoryImpl repository;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @Autowired
    VehicleService vehicleService;

    public List<? extends Booking> getAllBookings() throws Exception {
        var bookingList = repository.getAll();
        if (bookingList.size() < 1) {
            return null;
        }
        return bookingList;
    }

    public Booking getBookingById(UUID id) throws Exception {
        var booking = repository.getById(id).orElse(null);
        return booking;
    }

    public Booking getBookingByBookingNumber(String bookingNumber) throws Exception {
        var booking = repository.getByBookingNumber(bookingNumber).orElse(null);
        return booking;
    }

    public Booking createBooking(Booking booking) throws Exception {
        var bookingEntity = new BookingEntity(booking);
        List<Vehicle> vehicleList = new ArrayList<>();
        for (var vehicle:
             booking.getVehicles()) {
            vehicleList.add(vehicleService.getVehicleById(vehicle.getId()));
        }
        bookingEntity.setVehicles(vehicleList);
        bookingEntity.setTotalPrice(calculateTotalBookingPrice(bookingEntity));
        if (bookingEntity.isValid()) {
            repository.save(bookingEntity);
            attachObservers(bookingEntity);
            return bookingEntity;
        } else {
            return null;
        }
    }

    public Booking updateBooking(Booking booking) throws Exception {
        var bookingEntity = new BookingEntity(booking);
        if (bookingEntity.isValid()) {
            repository.update(bookingEntity);
            attachObservers(bookingEntity);
            return bookingEntity;
        } else {
            return null;
        }
    }

    public List<? extends Booking> getAllUnReturnedBookings() throws Exception {
        var bookingList = repository.getAllUnreturnedBookings();
        if (bookingList.size() < 1) {
            return null;
        }
        return bookingList;
    }

    public boolean notifyAllExpiredAndUnreturnedBookings() throws Exception {
        var bookingList = repository.getAllEndDatePassedAndUnReturned(new Date());
        if (bookingList.size() < 1) {
            return false;
        }
        for (var booking:
                bookingList) {
            var bookingEntity = new BookingEntity(booking);
            attachObservers(bookingEntity);
            bookingEntity.Notify();
            repository.update(bookingEntity);
        }
        return true;
    }

    public void attachObservers(BookingEntity booking) throws Exception {
        booking.Attach(booking.getUser());
        booking.Attach(booking.getCompany());
    }

    public BigDecimal calculateTotalBookingPrice(BookingEntity booking) throws Exception {
        BigDecimal totalPrice = new BigDecimal(0);
        var rentPeriodInMillis = Math.abs(booking.getStartDate().getTime() - booking.getEndDate().getTime());
        var rentalPeriodInDays = TimeUnit.DAYS.convert(rentPeriodInMillis, TimeUnit.MILLISECONDS);
        for (var vehicle:
             booking.getVehicles()) {
            totalPrice = totalPrice.add(vehicle.getPricePerDay().multiply(new BigDecimal(rentalPeriodInDays)));
        }
        return totalPrice;
    }

}
