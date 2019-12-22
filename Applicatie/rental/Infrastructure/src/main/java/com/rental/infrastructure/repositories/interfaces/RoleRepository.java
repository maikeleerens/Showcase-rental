package com.rental.infrastructure.repositories.interfaces;

import com.rental.domain.interfaces.entities.Role;
import com.rental.infrastructure.repositories.interfaces.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> getByName(String name);
}
