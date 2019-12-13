package com.rental.infrastructure.repositories;

import com.rental.domain.interfaces.entities.Vehicle;
import com.rental.infrastructure.datamodels.VehicleDataModel;
import com.rental.infrastructure.repositories.interfaces.VehicleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<? extends Vehicle> getAll() {
        try {
            String sql = "SELECT v FROM VehicleDataModel v";
            final TypedQuery<VehicleDataModel> query = em.createQuery(sql, VehicleDataModel.class);
            return query.getResultList();
        } catch(NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Vehicle> getById(UUID id) {
        try {
            String sql = "SELECT v FROM VehicleDataModel v WHERE id = :id";
            final TypedQuery<VehicleDataModel> query = em.createQuery(sql, VehicleDataModel.class);
            query.setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Vehicle> save(Vehicle vehicle) {
        var vehicleDataModel = new VehicleDataModel(vehicle);
        em.persist(vehicleDataModel);
        em.flush();
        return Optional.of(vehicleDataModel);
    }

    @Override
    public Optional<Vehicle> update(Vehicle vehicle) {
        var vehicleDataModel = new VehicleDataModel(vehicle);
        em.merge(vehicleDataModel);
        em.flush();
        return Optional.of(vehicleDataModel);
    }

    @Override
    public boolean delete(UUID id) {
        String sql = "DELETE FROM VehicleDataModel WHERE id = :id";
        final TypedQuery<VehicleDataModel> query = em.createQuery(sql, VehicleDataModel.class);
        if (query.executeUpdate() != 1) return false;
        return true;
    }

    @Override
    public Optional<Vehicle> getByLicencePlate(String licencePlate) {
        try {
            String sql = "SELECT v FROM VehicleDataModel v WHERE licence_plate = :licencePlate";
            final TypedQuery<VehicleDataModel> query = em.createQuery(sql, VehicleDataModel.class);
            query.setParameter("licencePlate", licencePlate);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
