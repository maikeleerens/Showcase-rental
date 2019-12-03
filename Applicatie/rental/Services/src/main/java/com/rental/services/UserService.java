package com.rental.services;

import com.rental.domain.interfaces.entities.User;
import com.rental.infrastructure.repositories.UserRepositoryImpl;
import com.rental.services.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepositoryImpl repository;

    public List<? extends User> getAllUsers() throws Exception {
        var userList = repository.getAll();
        if (userList.size() < 1) {
            return null;
        }
        return userList;
    }

    public User getUserById(UUID id) throws Exception {
        return repository.getById(id).orElse(null);
    }

    public List<? extends User> getUserByName(String name) throws Exception {
        var userList = repository.getByName(name);
        if (userList.size() < 1) {
            return null;
        }
        return userList;
    }

    public User createUser(User user) throws Exception {
        var userEntity = new UserEntity(user);
        if (userEntity.isValid()) {
            var createdUser = repository.save(userEntity);
            return createdUser.orElse(null);
        } else {
            return null;
        }
    }

    public User updateUser(User user) throws Exception {
        var userEntity = new UserEntity(user);
        if (userEntity.isValid()) {
            repository.update(userEntity).orElse(null);
            return userEntity;
        } else {
            return null;
        }
    }

    public List<String> getUserNotifications(UUID id) throws Exception {
        var notifications = repository.getAllUserNotifications(id);
        if (notifications.size() < 1) {
            return null;
        }
        return notifications;
    }

    public boolean exportNotificationsToText(List<String> notifications, String filePath) throws IOException {
        if (notifications == null || notifications.isEmpty()) return false;
        Path file = Paths.get(filePath+"/notifications.txt");
        Files.write(file, notifications, StandardCharsets.UTF_8);
        return true;
    }
}
