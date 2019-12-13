package com.rental.services;

import com.rental.domain.interfaces.entities.User;
import com.rental.infrastructure.repositories.UserRepositoryImpl;
import com.rental.services.models.UserEntity;
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
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepositoryImpl repository;

    @Test
    public void getAllUsers_Returns_List_Of_Users() throws Exception {
        //arrange
        User expectedUser = new UserEntity("TestNaam", "TestStraat", "TestStad");
        List<User> expectedUserList = Arrays.asList(expectedUser);

        //Mockito.when(repository.getAll()).thenReturn(expectedUserList);
        Mockito.doReturn(expectedUserList).when(repository).getAll();

        //act
        List<? extends User> actualUserList = service.getAllUsers();

        //assert
        assertThat(actualUserList).isEqualTo(expectedUserList);
    }

    @Test
    public void getUserById_Returns_User() throws Exception {
        //arrange
        User expectedUser = new UserEntity("TestNaam", "TestStraat", "TestStad");
        expectedUser.setId(UUID.fromString("56f3ad2f-8f6c-4f54-b1d2-54a544d71492"));

        Mockito.when(repository.getById(UUID.fromString("56f3ad2f-8f6c-4f54-b1d2-54a544d71492"))).thenReturn(Optional.of(expectedUser));

        //act
        User actualUser = service.getUserById(UUID.fromString("56f3ad2f-8f6c-4f54-b1d2-54a544d71492"));

        //assert
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    public void getUserById_Returns_Null_On_Non_Existing_Id() throws Exception {
        //arrange
        User user = new UserEntity("TestNaam", "TestStraat", "TestStad");
        user.setId(UUID.fromString("56f3ad2f-8f6c-4f54-b1d2-54a544d71492"));

        Mockito.when(repository.getById(UUID.fromString("56f3ad2f-8f6c-4f54-b1d2-54a544d71492"))).thenReturn(Optional.of(user));

        //act
        User actualUser = service.getUserById(UUID.fromString("b551d47f-2b7d-4e49-91c2-b68541b4fe5c"));

        //assert
        assertThat(actualUser).isNull();
    }

    @Test
    public void getUserByName_Returns_List_Of_Users() throws Exception {
        //arrange
        User expectedUser = new UserEntity("TestNaam", "TestStraat", "TestStad");
        List<User> expectedUserList = Arrays.asList(expectedUser);

        Mockito.doReturn(expectedUserList).when(repository).getByName("TestNaam");

        //act
        List<? extends User> actualUserList = service.getUserByName("TestNaam");

        //assert
        assertThat(actualUserList).isEqualTo(expectedUserList);
    }

    @Test
    public void getUserByName_Returns_Null_On_Non_Existing_Name() throws Exception {
        //arrange
        User user = new UserEntity("TestNaam", "TestStraat", "TestStad");
        List<User> expectedUserList = Arrays.asList(user);

        Mockito.doReturn(expectedUserList).when(repository).getByName("TestNaam");

        //act
        List<? extends User> actualUserList = service.getUserByName("Fake-Name");

        //assert
        assertThat(actualUserList).isNullOrEmpty();
    }

    @Test
    public void createUser_Returns_User() throws Exception {
        //arrange
        User userToCreate = new UserEntity("TestNaam", "TestStraat", "TestStad");

        Mockito.when(repository.save(userToCreate)).thenReturn(Optional.of(userToCreate));

        //act
        User actualUser = service.createUser(userToCreate);

        //assert
        assertThat(actualUser).isEqualTo(userToCreate);
    }

    @Test
    public void createUser_Returns_Null_On_Invalid_User() throws Exception {
        //arrange
        User userToCreate = new UserEntity("", "", "");

        Mockito.when(repository.save(userToCreate)).thenReturn(Optional.of(userToCreate));

        //act
        User actualUser = service.createUser(userToCreate);

        //assert
        assertThat(actualUser).isNull();
    }
}
