package com.rental.application.controllers;

import com.rental.domain.entities.Booking;
import com.rental.domain.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    BookingService service;

    @GetMapping
    public ResponseEntity GetAllBookings() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllBookings());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity GetBookingById(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getBookingById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/number/{number}")
    public ResponseEntity getBookingByNumber(@PathVariable String number) {
        try {
            if (number == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getBookingByBookingNumber(number));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    /**
     * @param booking
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity CreateBooking(@RequestBody Booking booking) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.createBooking(booking));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity EditBooking(@PathVariable UUID id, @RequestBody Booking booking) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.updateBooking(id, booking));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
