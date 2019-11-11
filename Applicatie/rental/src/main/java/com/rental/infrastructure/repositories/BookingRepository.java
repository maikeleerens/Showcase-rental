package com.rental.infrastructure.repositories;

import com.rental.domain.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByBookingNumber(String bookingNumber);
    List<Booking> findBookingsByIsReturnedFalse();
   @Query(value = "SELECT * FROM Bookings WHERE end_date < :currentDate AND returned = false", nativeQuery = true)
    List<Booking> findAllEndDatePassedAndUnReturned(Date currentDate);
}
