package com.rental.api.controllers;

import com.rental.api.viewmodels.booking.BookingViewModel;
import com.rental.api.viewmodels.booking.CreateBookingViewModel;
import com.rental.services.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@Api(tags = {"Booking management"})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully executed request"),
        @ApiResponse(code = 400, message = "Failed to execute request"),
        @ApiResponse(code = 401, message = "Unauthorized: Invalid username or password"),
        @ApiResponse(code = 403, message = "Unauthorized: No access to resource"),
        @ApiResponse(code = 404, message = "Resource not found")
})
public class BookingController {

    //region Private attributes
    private BookingService _service;
    //endregion

    @Autowired
    public BookingController(BookingService service) {
        _service = service;
    }

    @ApiOperation(value = "Gets all bookings",  response = BookingViewModel.class, responseContainer = "List")
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping
    public ResponseEntity getAllBookings() {
        try {
            var bookings = _service.getAllBookings();
            if (bookings == null) return ResponseEntity.status(HttpStatus.OK).body("No bookings found");
            return ResponseEntity.status(HttpStatus.OK).body(BookingViewModel.toBookingViewModels(bookings));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets a booking by id", response = BookingViewModel.class, consumes = "test")
    @PostAuthorize("returnObject.body.user.id == authentication.principal.id OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/id/{id}")
    public ResponseEntity getBookingById(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            var booking = _service.getBookingById(id);
            if (booking == null) return ResponseEntity.status(HttpStatus.OK).body("No booking found with id: " + id);
            var test = ResponseEntity.status(HttpStatus.OK).body(new BookingViewModel(booking));
            return ResponseEntity.status(HttpStatus.OK).body(new BookingViewModel(booking));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets a booking by booking number", response = BookingViewModel.class)
    @PostAuthorize("returnObject.body.user.id == authentication.principal.id OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/number/{bookingNumber}")
    public ResponseEntity getBookingByNumber(@PathVariable String bookingNumber) {
        try {
            if (bookingNumber == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking number is empty");
            var booking = _service.getBookingByBookingNumber(bookingNumber);
            if (booking == null) return ResponseEntity.status(HttpStatus.OK).body("No booking found with booking number: " + bookingNumber);
            return ResponseEntity.status(HttpStatus.OK).body(new BookingViewModel(booking));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Creates a new booking", response = BookingViewModel.class)
    @PostMapping("/create")
    public ResponseEntity createBooking(@RequestBody CreateBookingViewModel createBookingViewModel) {
        try {
            var createdBooking = _service.createBooking(BookingViewModel.toBookingViewModel(createBookingViewModel));
            if (createdBooking == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create booking");
            return ResponseEntity.status(HttpStatus.OK).body(new BookingViewModel(createdBooking));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets all currently un-returned bookings", response = BookingViewModel.class, responseContainer = "List")
    @GetMapping("/unreturned")
    public ResponseEntity getAllUnReturnedBookings() {
        try {
            var bookings = _service.getAllUnReturnedBookings();
            if (bookings == null)
                return ResponseEntity.status(HttpStatus.OK).body("No un returned bookings found!");
            return ResponseEntity.status(HttpStatus.OK).body(BookingViewModel.toBookingViewModels(bookings));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }

    @ApiOperation(value = "Returns a booking by booking number", response = BookingViewModel.class)
    @PostMapping("/return/{bookingNumber}")
    public ResponseEntity returnBookingByBookingNumber(@PathVariable String bookingNumber) {
        try {
            if (bookingNumber == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking number is empty");
            var bookingToReturn = _service.getBookingByBookingNumber(bookingNumber);
            if (bookingToReturn == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking " + bookingNumber + " does not exist");
            var bookingViewModel = new BookingViewModel(bookingToReturn);
            bookingViewModel.setReturned(true);
            var updatedBooking = _service.updateBooking(bookingViewModel);
            if (updatedBooking == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to return booking");
            return ResponseEntity.status(HttpStatus.OK).body(new BookingViewModel(updatedBooking));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Notifies all users/companies for un-returned and expired bookings", response = String.class)
    @GetMapping("/notify")
    public ResponseEntity notifyBookingObservers() {
        try {
            var bookingsToNotify = _service.notifyAllExpiredAndUnreturnedBookings();
            if (!bookingsToNotify)
                return ResponseEntity.status(HttpStatus.OK).body("No notifications send");
            return ResponseEntity.status(HttpStatus.OK).body("Notifications send!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
