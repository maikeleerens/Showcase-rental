package com.rental.services;

import com.rental.domain.entities.Vehicle;
import com.rental.infrastructure.repositories.VehicleRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.Silent.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService service;

    @Mock
    private VehicleRepository repository;

    //region Tests
    @Test
    public void getAllVehicles_Returns_List_Of_Vehicles() throws Exception {
        //Arrange
        Vehicle vehicle = new Vehicle("TestPlate", "TestName", new BigDecimal("10"), 10);
        List<Vehicle> expectedVehicles = Arrays.asList(vehicle);

        Mockito.when(repository.findAll()).thenReturn(expectedVehicles);

        //Act
        List<Vehicle> actualVehicleList = service.getAllVehicles();

        //Assert
        assertThat(actualVehicleList).isEqualTo(expectedVehicles);
    }

    @Test
    public void getVehicleById_Returns_Vehicle() throws Exception {
        //Arrange
        Vehicle expectedVehicle = new Vehicle("TestPlate", "TestName", new BigDecimal("10"), 10);
        expectedVehicle.setId(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"));

        Mockito.when(repository.findById(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"))).thenReturn(Optional.of(expectedVehicle));

        //act
        Vehicle actualVehicle = service.getVehicleById(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"));

        //assert
        assertThat(actualVehicle).isEqualTo(expectedVehicle);
    }

    @Test
    public void getVehicleById_Returns_Null_On_Non_Existing_Id() throws Exception {
        //Arrange
        Vehicle vehicle = new Vehicle("TestPlate", "TestName", new BigDecimal("10"), 10);
        vehicle.setId(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"));

        Mockito.when(repository.findById(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"))).thenReturn(Optional.of(vehicle));

        //act
        Vehicle actualVehicle = service.getVehicleById(UUID.fromString("4333d86e-8834-46ea-bcf5-5035a900ed8f"));

        //assert
        assertThat(actualVehicle).isNull();
    }

    @Test
    public void getVehicleByLicencePlate_Returns_Vehicle() throws Exception {
        //arrange
        Vehicle expectedVehicle = new Vehicle("TestPlate", "TestName", new BigDecimal("10"), 10);

        Mockito.when(repository.findByLicencePlate("TestPlate")).thenReturn(Optional.of(expectedVehicle));

        //act
        Vehicle actualVehicle = service.getByLicencePlate("TestPlate");

        //assert
        assertThat(actualVehicle).isEqualTo(expectedVehicle);
    }

    @Test
    public void getVehicleByLicencePlate_Returns_Null_On_Non_Existing_LicencePlate() throws Exception {
        //arrange
        Vehicle vehicle = new Vehicle("TestPlate", "TestName", new BigDecimal("10"), 10);

        Mockito.when(repository.findByLicencePlate("TestPlate")).thenReturn(Optional.of(vehicle));

        //act
        Vehicle actualVehicle = service.getByLicencePlate("Fake-Licence-Plate");

        //assert
        assertThat(actualVehicle).isNull();
    }

    @Test
    public void createVehicle_Returns_Vehicle() throws Exception {
        //arrange
        Vehicle vehicleToCreate = new Vehicle("TestPlate", "TestName", new BigDecimal("10"), 10);

        Mockito.when(repository.save(vehicleToCreate)).thenReturn(vehicleToCreate);

        //act
        Vehicle actualCreatedVehicle = service.createVehicle(vehicleToCreate);

        //assert
        assertThat(actualCreatedVehicle).isNotNull();
    }

    @Test
    public void createVehicle_Returns_Null_On_Invalid_Input_Parameters() throws Exception {
        //arrange
        Vehicle vehicleToCreate = new Vehicle("", "", new BigDecimal("10"), 10);

        Mockito.when(repository.save(vehicleToCreate)).thenReturn(vehicleToCreate);

        //act
        Vehicle actualVehicle = service.createVehicle(vehicleToCreate);

        //assert
        assertThat(actualVehicle).isNull();
    }

    //endregion Tests
}
