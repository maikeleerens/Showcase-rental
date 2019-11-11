package com.rental.domain.services;

import com.rental.domain.entities.Booking;
import com.rental.infrastructure.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    BookingRepository repository;

    public List<Booking> getAllBookings() throws Exception {
        var bookingList = repository.findAll();
        if (bookingList.size() < 1) {
            return null;
        }
        for (var booking:
             bookingList) {
            attachObservers(booking);
        }
        return bookingList;
}

    public Booking getBookingById(UUID id) throws Exception {
        var booking = repository.findById(id).orElse(null);
        if (booking != null) {
            attachObservers(booking);
        }
        return booking;
    }

    public Booking getBookingByBookingNumber(String bookingNumber) throws Exception {
        var booking = repository.findByBookingNumber(bookingNumber).orElse(null);
        if (booking != null) {
            attachObservers(booking);
        }
        return booking;
    }

    public Booking createBooking(Booking booking) throws Exception {
        if (booking.isValid()) {
            repository.save(booking);
            attachObservers(booking);
            return booking;
        } else {
            return null;
        }
    }

    public Booking updateBooking(UUID id, Booking booking) throws Exception {
        var bookingToUpdate = getBookingById(id);
        if (!bookingToUpdate.isValid())
            return null;

        if (booking.isValid()) {
            booking.setId(id);
            repository.save(booking);
            attachObservers(booking);
            return booking;
        } else {
            return null;
        }
    }

    public List<Booking> getAllUnReturnedBookings() throws Exception {
        var bookingList = repository.findBookingsByIsReturnedFalse();
        if (bookingList.size() < 1) {
            return null;
        }
        for (var booking:
                bookingList) {
            attachObservers(booking);
        }
        return bookingList;
    }

    public List<Booking> getAllExpiredAndUnReturnedBookings() throws Exception {
        var bookingList = repository.findAllEndDatePassedAndUnReturned(new Date());
        if (bookingList.size() < 1) {
            return null;
        }
        for (var booking:
                bookingList) {
            attachObservers(booking);
            booking.Notify();
            repository.save(booking);
        }
        return bookingList;
    }

    public void attachObservers(Booking booking) throws Exception {
        booking.Attach(booking.getUser());
        booking.Attach(booking.getCompany());
    }

}
