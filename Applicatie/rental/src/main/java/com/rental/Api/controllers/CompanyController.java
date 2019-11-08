package com.rental.Api.controllers;

import com.rental.domain.entities.Company;
import com.rental.domain.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    CompanyService service;

    @GetMapping
    public ResponseEntity getAllCompanies() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllCompanies());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getCompanyById(@PathVariable("id") UUID id) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            return ResponseEntity.status(HttpStatus.OK).body(service.getCompanyById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity createCompany(@RequestBody Company company) {
        try {
            if (!company.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company is not valid");
            var createdCompany = service.createCompany(company);
            if (createdCompany == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create company");
            return ResponseEntity.status(HttpStatus.OK).body(createdCompany);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editCompany(@PathVariable("id") UUID id, @RequestBody Company company) {
        try {
            if (id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is empty");
            if (!company.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid company");
            var updatedCompany = service.updateCompany(id, company);
            if (updatedCompany == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update company");
            return ResponseEntity.status(HttpStatus.OK).body(updatedCompany);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
