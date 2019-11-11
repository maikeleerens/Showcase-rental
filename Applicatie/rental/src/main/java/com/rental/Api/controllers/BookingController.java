package com.rental.Api.controllers;

import com.rental.domain.entities.Booking;
import com.rental.domain.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    BookingService service;

    @GetMapping
    public ResponseEntity getAllBookings() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllBookings());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getBookingById(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getBookingById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/number/{bookingNumber}")
    public ResponseEntity getBookingByNumber(@PathVariable String bookingNumber) {
        try {
            if (bookingNumber == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking number is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getBookingByBookingNumber(bookingNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity createBooking(@RequestBody Booking booking) {
        try {
            if (!booking.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking is invalid");
            var createdBooking = service.createBooking(booking);
            if (createdBooking == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create booking");
            return ResponseEntity.status(HttpStatus.OK).body(createdBooking);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editBooking(@PathVariable UUID id, @RequestBody Booking booking) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            if (!booking.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking is invalid");
            var updatedBooking = service.updateBooking(id, booking);
            if (updatedBooking == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update booking");
            return ResponseEntity.status(HttpStatus.OK).body(updatedBooking);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/unreturned")
    public ResponseEntity getAllUnReturnedBookings() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllUnReturnedBookings());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }

    @PostMapping("/return/{bookingNumber}")
    public ResponseEntity returnBookingByBookingNumber(@PathVariable String bookingNumber) {
        try {
            if (bookingNumber == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking number is empty");
            var bookingToReturn = service.getBookingByBookingNumber(bookingNumber);
            if (bookingToReturn == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking does not exist");
            bookingToReturn.setReturned(true);
            var updatedBooking = service.updateBooking(bookingToReturn.getId(), bookingToReturn);
            if (updatedBooking == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update booking");
            return ResponseEntity.status(HttpStatus.OK).body(updatedBooking);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/notify")
    public ResponseEntity notifyBookingObservers() {
        try {
            var bookings = service.getAllExpiredAndUnReturnedBookings();
            return ResponseEntity.status(HttpStatus.OK).body(bookings);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
