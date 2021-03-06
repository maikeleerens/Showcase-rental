package com.rental.services;

import com.rental.domain.interfaces.entities.Vehicle;
import com.rental.infrastructure.repositories.VehicleRepositoryImpl;
import com.rental.services.models.VehicleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.Silent.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService _service;

    @Mock
    private VehicleRepositoryImpl _repository;

    //region Tests
    @Test
    public void getAllVehicles_Returns_List_Of_Vehicles() throws Exception {
        //Arrange
        Vehicle vehicle = new VehicleEntity("TestPlate", "TestName", new BigDecimal("10"), 10);
        List<? extends Vehicle> expectedVehicles = Arrays.asList(vehicle);

        Mockito.doReturn(expectedVehicles).when(_repository).getAll();

        //Act
        List<? extends Vehicle> actualVehicleList = _service.getAllVehicles();

        //Assert
        assertThat(actualVehicleList).isEqualTo(expectedVehicles);
    }

    @Test
    public void getVehicleById_Returns_Vehicle() throws Exception {
        //Arrange
        Vehicle expectedVehicle = new VehicleEntity("TestPlate", "TestName", new BigDecimal("10"), 10);
        expectedVehicle.setId(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"));

        Mockito.when(_repository.getById(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"))).thenReturn(Optional.of(expectedVehicle));

        //act
        Vehicle actualVehicle = _service.getVehicleById(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"));

        //assert
        assertThat(actualVehicle).isEqualTo(expectedVehicle);
    }

    @Test
    public void getVehicleById_Returns_Null_On_Non_Existing_Id() throws Exception {
        //Arrange
        Vehicle vehicle = new VehicleEntity("TestPlate", "TestName", new BigDecimal("10"), 10);
        vehicle.setId(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"));

        Mockito.when(_repository.getById(UUID.fromString("1fda32e4-2d2d-4113-938c-07f753d21fb7"))).thenReturn(Optional.of(vehicle));

        //act
        Vehicle actualVehicle = _service.getVehicleById(UUID.fromString("4333d86e-8834-46ea-bcf5-5035a900ed8f"));

        //assert
        assertThat(actualVehicle).isNull();
    }

    @Test
    public void getVehicleByLicencePlate_Returns_Vehicle() throws Exception {
        //arrange
        Vehicle expectedVehicle = new VehicleEntity("TestPlate", "TestName", new BigDecimal("10"), 10);

        Mockito.when(_repository.getByLicencePlate("TestPlate")).thenReturn(Optional.of(expectedVehicle));

        //act
        Vehicle actualVehicle = _service.getByLicencePlate("TestPlate");

        //assert
        assertThat(actualVehicle).isEqualTo(expectedVehicle);
    }

    @Test
    public void getVehicleByLicencePlate_Returns_Null_On_Non_Existing_LicencePlate() throws Exception {
        //arrange
        Vehicle vehicle = new VehicleEntity("TestPlate", "TestName", new BigDecimal("10"), 10);

        Mockito.when(_repository.getByLicencePlate("TestPlate")).thenReturn(Optional.of(vehicle));

        //act
        Vehicle actualVehicle = _service.getByLicencePlate("Fake-Licence-Plate");

        //assert
        assertThat(actualVehicle).isNull();
    }

    @Test
    public void createVehicle_Returns_Created_Vehicle() throws Exception {
        //arrange
        Vehicle vehicleToCreate = new VehicleEntity("TestPlate", "TestName", new BigDecimal("10"), 10);

        Mockito.when(_repository.save(vehicleToCreate)).thenReturn(Optional.of(vehicleToCreate));

        //act
        Vehicle actualCreatedVehicle = _service.createVehicle(vehicleToCreate);

        //assert
        assertThat(actualCreatedVehicle).isNotNull();
    }

    @Test
    public void createVehicle_Returns_Null_On_Invalid_Input_Parameters() throws Exception {
        //arrange
        Vehicle vehicleToCreate = new VehicleEntity("", "", new BigDecimal("10"), 10);

        Mockito.when(_repository.save(vehicleToCreate)).thenReturn(Optional.of(vehicleToCreate));

        //act
        Vehicle actualVehicle = _service.createVehicle(vehicleToCreate);

        //assert
        assertThat(actualVehicle).isNull();
    }

    //endregion Tests
}
