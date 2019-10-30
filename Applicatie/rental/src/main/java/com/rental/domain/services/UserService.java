package com.rental.domain.services;

import com.rental.domain.entities.User;
import com.rental.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> GetAllUsers() {
        List<User> userList = repository.findAll();

        return userList;
    }
}
