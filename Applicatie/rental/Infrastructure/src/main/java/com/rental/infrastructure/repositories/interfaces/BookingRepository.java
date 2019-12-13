package com.rental.infrastructure.repositories.interfaces;

import com.rental.domain.interfaces.entities.Booking;
import com.rental.infrastructure.repositories.interfaces.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends BaseRepository<Booking> {
    Optional<Booking> getByBookingNumber(String bookingNumber);
    List<? extends Booking> getAllUnreturnedBookings();
   //@Query(value = "SELECT * FROM Bookings WHERE end_date < :currentDate AND returned = false", nativeQuery = true)
    List<? extends Booking> getAllEndDatePassedAndUnReturned(Date currentDate);
}
