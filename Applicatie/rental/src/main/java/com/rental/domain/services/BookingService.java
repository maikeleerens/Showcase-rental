package com.rental.domain.services;

import com.rental.domain.entities.Booking;
import com.rental.infrastructure.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    BookingRepository repository;

    public List<Booking> getAllBookings() throws Exception {
        var bookingList = repository.findAll();
        if (bookingList.size() < 1) {
            return Arrays.asList(new Booking("No bookings found"));
        }
        return repository.findAll();
    }

    public Booking getBookingById(UUID id) throws Exception {
        return repository.findById(id).orElse(new Booking("No booking found with id: " + id));
    }

    public Booking getBookingByBookingNumber(String bookingNumber) throws Exception {
        return repository.findByBookingNumber(bookingNumber).orElse(new Booking("No booking found with booking number: " + bookingNumber));
    }

    public Booking createBooking(Booking booking) throws Exception {
        if (booking.isValid()) {
            repository.save(booking);
            return booking;
        } else {
            return new Booking("Booking is invalid");
        }
    }

    public Booking updateBooking(UUID id, Booking booking) throws Exception {
        var bookingToUpdate = getBookingById(id);
        if (!bookingToUpdate.isValid())
            return new Booking("Booking to update is invalid");

        if (!booking.getError() && booking.isValid()) {
            booking.setId(id);
            repository.save(booking);
            return booking;
        } else {
            return new Booking("Invalid booking");
        }
    }
}
