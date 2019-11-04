package com.rental.domain.services;

import com.rental.domain.entities.Company;
import com.rental.infrastructure.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository repository;

    public List<Company> getAllCompanies() throws Exception {
        var vehicleList = repository.findAll();
        if (vehicleList.size() < 1) {
            return Arrays.asList(new Company("No Companies found"));
        }
        return repository.findAll();
    }

    public Company getCompanyById(UUID id) throws Exception {
        return repository.findById(id).orElse(new Company("No company found with id: " + id));
    }

    public Company createCompany(Company company) throws Exception {
        if (company.isValid()) {
            repository.save(company);
            return company;
        } else {
            return new Company("Company is invalid");
        }
    }

    public Company updateCompany(UUID id, Company company) throws Exception {
        var companyToUpdate = getCompanyById(id);
        if (!companyToUpdate.isValid())
            return new Company("Company to update is invalid");

        if (!company.getError() && company.isValid()) {
            company.setId(id);
            repository.save(company);
            return company;
        } else {
            return new Company("Invalid company");
        }
    }
}
