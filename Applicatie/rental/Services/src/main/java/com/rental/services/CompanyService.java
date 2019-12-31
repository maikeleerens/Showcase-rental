package com.rental.services;

import com.rental.domain.interfaces.entities.Company;
import com.rental.infrastructure.repositories.CompanyRepositoryImpl;
import com.rental.services.models.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {

    //region Private attributes
    private CompanyRepositoryImpl _repository;
    //endregion

    @Autowired
    public CompanyService(CompanyRepositoryImpl repository) {
        _repository = repository;
    }

    public List<? extends Company> getAllCompanies() throws Exception {
        var companyList = _repository.getAll();
        if (companyList.size() < 1) {
            return null;
        }
        return companyList;
    }

    public Company getCompanyById(UUID id) throws Exception {
        return _repository.getById(id).orElse(null);
    }

    public Company createCompany(Company company) throws Exception {
        var companyEntity = new CompanyEntity(company);
        if (companyEntity.isValid()) {
            var createdCompany = _repository.save(companyEntity);
            return createdCompany.orElse(null);
        } else {
            return null;
        }
    }

    public Company updateCompany(Company company) throws Exception {
        var companyEntity = new CompanyEntity(company);
        if (companyEntity.isValid()) {
            var updatedCompany = _repository.update(companyEntity);
            return updatedCompany.orElse(null);
        } else {
            return null;
        }
    }

    public List<String> getCompanyNotifications(UUID id) throws Exception {
        var notifications = _repository.getAllCompanyNotifications(id);
        if (notifications.size() < 1) {
            return null;
        }
        return notifications;
    }

    public boolean exportNotificationsToText(List<String> notifications, String filePath) throws IOException {
        if (notifications == null || notifications.isEmpty()) return false;
        Path file = Paths.get(filePath+"/notifications.txt");
        Files.write(file, notifications, StandardCharsets.UTF_8);
        return true;
    }
}
