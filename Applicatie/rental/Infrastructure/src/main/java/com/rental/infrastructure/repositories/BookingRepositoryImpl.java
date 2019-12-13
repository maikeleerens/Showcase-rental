package com.rental.infrastructure.repositories;

import com.rental.domain.interfaces.entities.Booking;
import com.rental.infrastructure.datamodels.BookingDataModel;
import com.rental.infrastructure.repositories.interfaces.BookingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Repository
@Transactional
public class BookingRepositoryImpl implements BookingRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<? extends Booking> getAll() {
        try {
            String sql = "SELECT b FROM BookingDataModel b";
            final TypedQuery<BookingDataModel> query = em.createQuery(sql, BookingDataModel.class);
            return query.getResultList();
        } catch(NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Booking> getById(UUID id) {
        try {
            String sql = "SELECT b FROM BookingDataModel b WHERE id = :id";
            final TypedQuery<BookingDataModel> query = em.createQuery(sql, BookingDataModel.class);
            query.setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Booking> save(Booking booking) {
        var bookingDataModel = new BookingDataModel(booking);
        em.persist(bookingDataModel);
        em.flush();
        return Optional.of(bookingDataModel);
    }

    @Override
    public Optional<Booking> update(Booking booking) {
        try {
            var bookingDataModel = new BookingDataModel(booking);
            em.merge(bookingDataModel);
            em.flush();
            return Optional.of(bookingDataModel);
        } catch (Exception ex){
            return Optional.empty();
        }

    }

    @Override
    public boolean delete(UUID id) {
        String sql = "DELETE FROM BookingDataModel WHERE id = :id";
        final TypedQuery<BookingDataModel> query = em.createQuery(sql, BookingDataModel.class);
        query.setParameter("id", id);
        if (query.executeUpdate() != 1) return false;
        return true;
    }

    @Override
    public Optional<Booking> getByBookingNumber(String bookingNumber) {
        try {
            String sql = "SELECT b FROM BookingDataModel b WHERE bookingNumber = :bookingNumber";
            final TypedQuery<BookingDataModel> query = em.createQuery(sql, BookingDataModel.class);
            query.setParameter("bookingNumber", bookingNumber);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<? extends Booking> getAllUnreturnedBookings() {
        try {
            String sql = "SELECT b FROM BookingDataModel b WHERE returned = false";
            final TypedQuery<BookingDataModel> query = em.createQuery(sql, BookingDataModel.class);
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<? extends Booking> getAllEndDatePassedAndUnReturned(Date currentDate) {
        try {
            String sql = "SELECT b FROM BookingDataModel b WHERE end_date < :currentDate AND returned = false";
            final TypedQuery<BookingDataModel> query = em.createQuery(sql, BookingDataModel.class);
            query.setParameter("currentDate", currentDate);
            return query.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }
}
