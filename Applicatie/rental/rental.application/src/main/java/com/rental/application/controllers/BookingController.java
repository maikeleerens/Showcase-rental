package com.rental.application.controllers;

import com.rental.domain.entities.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    //Gets all bookings
    //returns code 200: All bookings successfully fetched
    //returns code 400: Something went wrong
    @GetMapping("/api/bookings")
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

    //Get a booking by id
    //returns code 200: Booking successfully fetched
    //returns code 400: Id is empty
    @GetMapping("/api/bookings/{id}")
    public ResponseEntity GetBookingById(@PathVariable String id)
    {
        try
        {
            if ((id == null) || (id.trim().isEmpty()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body("GetBookingById");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //Create a new booking
    //returns code 200: New booking successfully created
    //returns code 400: Booking is empty
    @PostMapping("/api/booking/create")
    public ResponseEntity CreateBooking(@RequestBody Booking booking)
    {
        try
        {
            if (booking.IsNullOrEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking is empty");

            //Attach user to booking
            booking.Attach(booking.getUser());
            return ResponseEntity.status(HttpStatus.OK).body(booking);
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //Update an existing booking
    //returns code 200: Booking successfully updated
    //returns code 400: Booking or id is empty
    @PutMapping("/api/bookings/edit/{id}")
    public ResponseEntity EditBooking(@PathVariable String id, @RequestBody Booking booking)
    {
        try
        {
            if ((id == null) || (id.trim().isEmpty()) || (booking.IsNullOrEmpty()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id or booking is empty");
            return ResponseEntity.status(HttpStatus.OK).body("Booking with id " + id + " has been updated");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
