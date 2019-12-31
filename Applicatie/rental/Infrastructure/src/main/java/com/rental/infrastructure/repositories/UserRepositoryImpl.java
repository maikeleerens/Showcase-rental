package com.rental.infrastructure.repositories;

import com.rental.domain.interfaces.entities.User;
import com.rental.infrastructure.datamodels.UserDataModel;
import com.rental.infrastructure.repositories.interfaces.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    //region Private attributes
    @PersistenceContext
    private EntityManager em;
    //endregion

    @Override
    public List<? extends User> getAll() {
        try {
            String sql = "SELECT u FROM UserDataModel u";
            final TypedQuery<UserDataModel> query = em.createQuery(sql, UserDataModel.class);
            return query.getResultList();
        } catch(NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<User> getById(UUID id) {
        try {
            String sql = "SELECT u FROM UserDataModel u WHERE id = :id";
            final TypedQuery<UserDataModel> query = em.createQuery(sql, UserDataModel.class);
            query.setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> save(User user) {
        var userDataModel = new UserDataModel(user);
        em.persist(userDataModel);
        em.flush();
        return Optional.of(userDataModel);
    }

    @Override
    public Optional<User> update(User user) {
        var userDataModel = new UserDataModel(user);
        em.merge(userDataModel);
        em.flush();
        return Optional.of(userDataModel);
    }

    @Override
    public boolean delete(UUID id) {
        String sql = "DELETE FROM UserDataModel WHERE id = :id";
        final TypedQuery<UserDataModel> query = em.createQuery(sql, UserDataModel.class);
        query.setParameter("id", id);
        if (query.executeUpdate() != 1) return false;
        return true;
    }

    @Override
    public List<? extends User> getByName(String name) {
        try {
            String sql = "SELECT u FROM UserDataModel u WHERE name = :name";
            final TypedQuery<UserDataModel> query = em.createQuery(sql, UserDataModel.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try {
            String sql = "SELECT u FROM UserDataModel u WHERE email = :email";
            final TypedQuery<UserDataModel> query = em.createQuery(sql, UserDataModel.class);
            query.setParameter("email", email);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<String> getAllUserNotifications(UUID id) {
        try {
            String sql = "SELECT notification FROM user_notifications WHERE user_id = :id";
            final Query query = em.createNativeQuery(sql);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }
}
