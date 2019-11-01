package com.rental.domain.services;

import com.rental.domain.entities.User;
import com.rental.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getAllUsers() throws Exception {
        var userList = repository.findAll();
        if (userList.size() < 1) {
            return Arrays.asList(new User("No users found"));
        }
        return repository.findAll();
    }

    public User getUserById(UUID id) throws Exception {
        return repository.findById(id).orElse(new User("No user found with id: " + id));
    }

    public List<User> getUserByName(String name) throws Exception {
        return repository.findByName(name).orElse(Arrays.asList(new User("No users found with name: " + name)));
    }

    public User createUser(User user) throws Exception {
        if (user.isValid()) {
            repository.save(user);
            return user;
        } else {
            return new User("User is invalid");
        }
    }

    public User updateUser(UUID id, User user) throws Exception {
        var userToUpdate = getUserById(id);
        if (!userToUpdate.isValid())
            return new User("User to update is invalid");

        if (!user.getError() && user.isValid()) {
            user.setId(id);
            repository.save(user);
            return user;
        } else {
            return new User("Invalid user");
        }
    }
}
