package com.rental.application.controllers;

import com.rental.domain.entities.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class BookingController {

    @GetMapping("/bookings")
    public ResponseEntity GetAllBookings()
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body("GetAllBookings");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity GetBookingById(@PathVariable String id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body("GetBookingById");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/booking/create")
    public ResponseEntity CreateBooking(@RequestBody Booking booking)
    {

    }
}
