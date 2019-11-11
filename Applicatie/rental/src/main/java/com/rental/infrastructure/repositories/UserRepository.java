package com.rental.infrastructure.repositories;

import com.rental.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByName(String name);
    @Query(value = "SELECT notification FROM User_Notifications WHERE user_id = :id", nativeQuery = true)
    List<String> findAllUserNotifications(UUID id);
}
