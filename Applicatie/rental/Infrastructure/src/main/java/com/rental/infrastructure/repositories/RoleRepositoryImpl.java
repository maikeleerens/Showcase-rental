package com.rental.infrastructure.repositories;

import com.rental.domain.interfaces.entities.Role;
import com.rental.infrastructure.datamodels.RoleDataModel;
import com.rental.infrastructure.repositories.interfaces.RoleRepository;
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
public class RoleRepositoryImpl implements RoleRepository {

    //region Private attributes
    @PersistenceContext
    private EntityManager em;
    //endregion

    @Override
    public List<? extends Role> getAll() {
        try {
            String sql = "SELECT r FROM RoledataModel r";
            final TypedQuery<RoleDataModel> query = em.createQuery(sql, RoleDataModel.class);
            return query.getResultList();
        } catch(NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Role> getById(UUID id) {
        try {
            String sql = "SELECT r FROM RoleDataModel r WHERE id = :id";
            final TypedQuery<RoleDataModel> query = em.createQuery(sql, RoleDataModel.class);
            query.setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Role> save(Role role) {
        var roleDataModel = new RoleDataModel(role);
        em.persist(roleDataModel);
        em.flush();
        return Optional.of(roleDataModel);
    }

    @Override
    public Optional<Role> update(Role role) {
        var roleDataModel = new RoleDataModel(role);
        em.merge(roleDataModel);
        em.flush();
        return Optional.of(roleDataModel);
    }

    @Override
    public boolean delete(UUID id) {
        String sql = "DELETE FROM RoleDataModel WHERE id = :id";
        final TypedQuery<RoleDataModel> query = em.createQuery(sql, RoleDataModel.class);
        query.setParameter("id", id);
        if (query.executeUpdate() != 1) return false;
        return true;
    }

    @Override
    public Optional<Role> getByName(String name) {
        try {
            String sql = "SELECT r FROM RoleDataModel r WHERE name = :name";
            final TypedQuery<RoleDataModel> query = em.createQuery(sql, RoleDataModel.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
