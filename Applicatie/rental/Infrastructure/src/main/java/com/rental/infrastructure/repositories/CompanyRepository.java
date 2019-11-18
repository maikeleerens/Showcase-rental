package com.rental.infrastructure.repositories;

import com.rental.domain.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    @Query(value = "SELECT notification FROM Company_Notifications WHERE company_id = :id", nativeQuery = true)
    List<String> findAllCompanyNotifications(UUID id);
}
