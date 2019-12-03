package com.rental.infrastructure.repositories;

import com.rental.domain.interfaces.entities.Company;
import com.rental.infrastructure.datamodels.CompanyDataModel;
import com.rental.infrastructure.repositories.interfaces.CompanyRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<? extends Company> getAll() {
        try {
            String sql = "SELECT c FROM CompanyDataModel c";
            final TypedQuery<CompanyDataModel> query = em.createQuery(sql, CompanyDataModel.class);
            return query.getResultList();
        } catch(NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Company> getById(UUID id) {
        try {
            String sql = "SELECT c FROM CompanyDataModel c WHERE id = :id";
            final TypedQuery<CompanyDataModel> query = em.createQuery(sql, CompanyDataModel.class);
            query.setParameter("id", id);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Company> save(Company company) {
        var companyDataModel = new CompanyDataModel(company);
        em.persist(companyDataModel);
        em.flush();
        return Optional.of(companyDataModel);
    }

    @Override
    public Optional<Company> update(Company company) {
        var companyDataModel = new CompanyDataModel(company);
        em.merge(companyDataModel);
        em.flush();
        return Optional.of(companyDataModel);
    }

    @Override
    public boolean delete(UUID id) {
        String sql = "DELETE FROM UserDataModel WHERE id = :id";
        final TypedQuery<CompanyDataModel> query = em.createQuery(sql, CompanyDataModel.class);
        query.setParameter("id", id);
        if (query.executeUpdate() != 1) return false;
        return true;
    }

    @Override
    public List<? extends Company> getByName(String name) {
        try {
            String sql = "SELECT u FROM UserDataModel u WHERE name = :name";
            final TypedQuery<CompanyDataModel> query = em.createQuery(sql, CompanyDataModel.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getAllCompanyNotifications(UUID id) {
        try {
            String sql = "SELECT notification FROM company_notifications WHERE company_id = :id";
            final Query query = em.createNativeQuery(sql);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }
}
