package com.rental.services;

import com.rental.domain.interfaces.entities.User;
import com.rental.infrastructure.repositories.UserRepositoryImpl;
import com.rental.services.models.RoleEntity;
import com.rental.services.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserRepositoryImpl _repository;
    private RoleService _roleService;
    //private PasswordEncoder _passwordEncoder;

    @Autowired
    public UserService(UserRepositoryImpl repository, RoleService roleService) {
        _repository = repository;
        _roleService = roleService;
        //_passwordEncoder = passwordEncoder;
    }

    public List<? extends User> getAllUsers() throws Exception {
        var userList = _repository.getAll();
        if (userList.size() < 1) {
            return null;
        }
        return userList;
    }

    public User getUserById(UUID id) throws Exception {
        return _repository.getById(id).orElse(null);
    }

    public List<? extends User> getUserByName(String name) throws Exception {
        var userList = _repository.getByName(name);
        if (userList.size() < 1) {
            return null;
        }
        return userList;
    }

    public User getUserByEmail(String email) throws Exception {
        return _repository.getByEmail(email).orElse(null);
    }

    public User createUser(User user) throws Exception {
        List<RoleEntity> roles = new ArrayList<>();
        var userEntity = new UserEntity(user);

        for (var role:
             userEntity.getRoles()) {
            var roleEntity = _roleService.getRoleByName(role.getName());
            if (roleEntity != null)
                roles.add(new RoleEntity(roleEntity));
        }
        userEntity.setRoles(roles);
        //userEntity.setPassword(_passwordEncoder.encode(userEntity.getPassword()));
        if (userEntity.isValid()) {
            var createdUser = _repository.save(userEntity);
            return createdUser.orElse(null);
        } else {
            return null;
        }
    }

    public User updateUser(User user) throws Exception {
        var userEntity = new UserEntity(user);
        if (userEntity.isValid()) {
            _repository.update(userEntity).orElse(null);
            return userEntity;
        } else {
            return null;
        }
    }

    public List<String> getUserNotifications(UUID id) throws Exception {
        var notifications = _repository.getAllUserNotifications(id);
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
