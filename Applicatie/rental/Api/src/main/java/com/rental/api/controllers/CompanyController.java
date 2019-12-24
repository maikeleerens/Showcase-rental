package com.rental.api.controllers;

import com.rental.api.viewmodels.company.CompanyViewModel;
import com.rental.api.viewmodels.company.CreateCompanyViewModel;
import com.rental.api.viewmodels.company.UpdateCompanyViewModel;
import com.rental.api.viewmodels.helpers.ViewModelHelper;
import com.rental.domain.interfaces.entities.Company;
import com.rental.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private CompanyService _service;

    @Autowired
    public CompanyController(CompanyService service) {
        _service = service;
    }

    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping
    public ResponseEntity getAllCompanies() {
        try {
            var companies = _service.getAllCompanies();
            if (companies == null) return ResponseEntity.status(HttpStatus.OK).body("No companies found");
            return ResponseEntity.status(HttpStatus.OK).body(ViewModelHelper.toCompanyViewModels(companies));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping("/id/{id}")
    public ResponseEntity getCompanyById(@PathVariable("id") UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            var company = _service.getCompanyById(id);
            if (company == null) return ResponseEntity.status(HttpStatus.OK).body("No company found with id: " + id);
            return ResponseEntity.status(HttpStatus.OK).body(new CompanyViewModel(company));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @PostMapping("/create")
    public ResponseEntity createCompany(@RequestBody CreateCompanyViewModel company) {
        try {
            //if (!company.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company is not valid");
            var createdCompany = _service.createCompany(ViewModelHelper.toCompanyViewModel(company));
            if (createdCompany == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create company");
            return ResponseEntity.status(HttpStatus.OK).body(new CompanyViewModel(createdCompany));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @PutMapping("/edit")
    public ResponseEntity editCompany(@RequestBody UpdateCompanyViewModel company) {
        try {
            //if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            //if (!company.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid company");
            var updatedCompany = _service.updateCompany(company);
            if (updatedCompany == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update company");
            return ResponseEntity.status(HttpStatus.OK).body(new CompanyViewModel(updatedCompany));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping("/notifications/{id}")
    public ResponseEntity getCompanyNotifications(@PathVariable UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            var notifications = _service.getCompanyNotifications(id);
            if (notifications == null) return ResponseEntity.status(HttpStatus.OK).body("No notifications found");
            return ResponseEntity.status(HttpStatus.OK).body(notifications);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @PostMapping("/notifications/export/{id}")
    public ResponseEntity exportNotificationsToFile(@PathVariable UUID id, @RequestBody String filePath) {
        try {
            if (_service.exportNotificationsToText(_service.getCompanyNotifications(id), filePath))
                return ResponseEntity.status(HttpStatus.OK).body("File successfully created");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create file");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
