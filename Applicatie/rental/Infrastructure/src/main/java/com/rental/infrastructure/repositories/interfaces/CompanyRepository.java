package com.rental.infrastructure.repositories.interfaces;

import com.rental.domain.interfaces.entities.Company;
import com.rental.infrastructure.repositories.interfaces.base.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends BaseRepository<Company> {
    List<? extends Company> getByName(String name);

    //@Query(value = "SELECT notification FROM Company_Notifications WHERE company_id = :id", nativeQuery = true)
    List<String> getAllCompanyNotifications(UUID id);
}
