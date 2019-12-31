package com.rental.api.controllers;

import com.rental.api.viewmodels.company.CompanyViewModel;
import com.rental.api.viewmodels.company.CreateCompanyViewModel;
import com.rental.api.viewmodels.company.UpdateCompanyViewModel;
import com.rental.services.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
@Api(tags = {"Company management"})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully executed request"),
        @ApiResponse(code = 400, message = "Failed to execute request"),
        @ApiResponse(code = 401, message = "Unauthorized: Invalid username or password"),
        @ApiResponse(code = 403, message = "Unauthorized: No access to resource"),
        @ApiResponse(code = 404, message = "Resource not found")
})
public class CompanyController {

    //region Private attributes
    private CompanyService _service;
    //endregion

    @Autowired
    public CompanyController(CompanyService service) {
        _service = service;
    }

    @ApiOperation(value = "Gets all companies", response = CompanyViewModel.class, responseContainer = "List")
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping
    public ResponseEntity getAllCompanies() {
        try {
            var companies = _service.getAllCompanies();
            if (companies == null) return ResponseEntity.status(HttpStatus.OK).body("No companies found");
            return ResponseEntity.status(HttpStatus.OK).body(CompanyViewModel.toCompanyViewModels(companies));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Gets a company by id", response = CompanyViewModel.class)
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

    @ApiOperation(value = "Creates a new company", response = CompanyViewModel.class)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @PostMapping("/create")
    public ResponseEntity createCompany(@RequestBody CreateCompanyViewModel company) {
        try {
            //if (!company.isValid()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company is not valid");
            var createdCompany = _service.createCompany(CompanyViewModel.toCompanyViewModel(company));
            if (createdCompany == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create company");
            return ResponseEntity.status(HttpStatus.OK).body(new CompanyViewModel(createdCompany));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Updates an existing company", response = CompanyViewModel.class)
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

    @ApiOperation(value = "Gets all notifications for a company by id", response = String.class, responseContainer = "List")
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

    @ApiOperation(value = "Gets all notifications as txt file for a company by id", response = String.class)
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
