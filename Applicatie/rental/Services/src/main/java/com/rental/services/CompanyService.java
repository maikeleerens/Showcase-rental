package com.rental.services;

import com.rental.domain.entities.Company;
import com.rental.infrastructure.repositories.CompanyRepository;
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

    @Autowired
    CompanyRepository repository;

    public List<Company> getAllCompanies() throws Exception {
        var vehicleList = repository.findAll();
        if (vehicleList.size() < 1) {
            return null;
        }
        return repository.findAll();
    }

    public Company getCompanyById(UUID id) throws Exception {
        return repository.findById(id).orElse(null);
    }

    public Company createCompany(Company company) throws Exception {
        if (company.isValid()) {
            repository.save(company);
            return company;
        } else {
            return null;
        }
    }

    public Company updateCompany(UUID id, Company company) throws Exception {
        var companyToUpdate = getCompanyById(id);
        if (!companyToUpdate.isValid())
            return null;

        if (company.isValid()) {
            company.setId(id);
            repository.save(company);
            return company;
        } else {
            return null;
        }
    }

    public List<String> getCompanyNotifications(UUID id) throws Exception {
        var notifications = repository.findAllCompanyNotifications(id);
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
