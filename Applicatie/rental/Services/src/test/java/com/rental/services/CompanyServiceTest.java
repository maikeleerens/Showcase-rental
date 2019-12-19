package com.rental.services;

import com.rental.domain.interfaces.entities.Company;
import com.rental.infrastructure.repositories.CompanyRepositoryImpl;
import com.rental.services.models.CompanyEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CompanyServiceTest {
    @InjectMocks
    CompanyService service;

    @Mock
    CompanyRepositoryImpl repository;

    @Test
    public void getAllCompanies_Returns_List_Of_Companies() throws Exception {
        //arrange
        Company expectedCompany = new CompanyEntity("TestNaam", "TestStraat");
        List<Company> expectedCompanyList = Arrays.asList(expectedCompany);

        Mockito.doReturn(expectedCompanyList).when(repository).getAll();

        //act
        List<? extends Company> actualCompanyList = service.getAllCompanies();

        //assert
        assertThat(actualCompanyList).isEqualTo(expectedCompanyList);
    }

    @Test
    public void getCompanyById_Returns_Company() throws Exception {
        //arrange
        Company expectedCompany = new CompanyEntity("TestNaam", "TestStraat");
        expectedCompany.setId(UUID.fromString("433f1c8b-61c3-4204-be73-f1697c147e22"));

        Mockito.when(repository.getById(UUID.fromString("433f1c8b-61c3-4204-be73-f1697c147e22"))).thenReturn(Optional.of(expectedCompany));

        //act
        Company actualCompany = service.getCompanyById(UUID.fromString("433f1c8b-61c3-4204-be73-f1697c147e22"));

        //arrange
        assertThat(actualCompany).isEqualTo(expectedCompany);
    }

    @Test
    public void getCompanyById_Returns_Null_On_Non_Existing_Id() throws Exception {
        //arrange
        Company expectedCompany = new CompanyEntity("TestNaam", "TestStraat");
        expectedCompany.setId(UUID.fromString("433f1c8b-61c3-4204-be73-f1697c147e22"));

        Mockito.when(repository.getById(UUID.fromString("433f1c8b-61c3-4204-be73-f1697c147e22"))).thenReturn(Optional.of(expectedCompany));

        //act
        Company actualCompany = service.getCompanyById(UUID.fromString("941c7b54-467b-49bc-9d35-b7b5810917c1"));

        //arrange
        assertThat(actualCompany).isNull();
    }
}
