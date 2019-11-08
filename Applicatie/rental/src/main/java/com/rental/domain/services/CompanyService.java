package com.rental.domain.services;

import com.rental.domain.entities.Company;
import com.rental.infrastructure.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
