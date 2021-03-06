package com.rental.infrastructure.repositories.interfaces;

import com.rental.domain.interfaces.entities.User;
import com.rental.infrastructure.repositories.interfaces.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByEmail(String email);

    List<? extends User> getByName(String name);

    List<String> getAllUserNotifications(UUID id);
}
