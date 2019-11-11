package com.rental.domain.services;

import com.rental.domain.entities.User;
import com.rental.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getAllUsers() throws Exception {
        var userList = repository.findAll();
        if (userList.size() < 1) {
            return null;
        }
        return userList;
    }

    public User getUserById(UUID id) throws Exception {
        return repository.findById(id).orElse(null);
    }

    public List<User> getUserByName(String name) throws Exception {
        var userList = repository.findByName(name);
        if (userList.size() < 1) {
            return null;
        }
        return userList;
    }

    public User createUser(User user) throws Exception {
        if (user.isValid()) {
            repository.save(user);
            return user;
        } else {
            return null;
        }
    }

    public User updateUser(UUID id, User user) throws Exception {
        var userToUpdate = getUserById(id);
        if (!userToUpdate.isValid())
            return null;

        if (user.isValid()) {
            user.setId(id);
            repository.save(user);
            return user;
        } else {
            return null;
        }
    }

    public List<String> getUserNotifications(UUID id) throws Exception {
        var notifications = repository.findAllUserNotifications(id);
        if (notifications.size() < 1) {
            return null;
        }
        return notifications;
    }
}
