package com.rental.services;

import com.rental.domain.interfaces.entities.Role;
import com.rental.infrastructure.repositories.RoleRepositoryImpl;
import com.rental.services.models.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleRepositoryImpl repository;

    public List<? extends Role> getAllRoles() throws Exception {
        var roleList = repository.getAll();
        if (roleList.size() < 1) {
            return null;
        }
        return roleList;
    }

    public Role getRoleById(UUID id) throws Exception {
        return repository.getById(id).orElse(null);
    }

    public Role createRole(Role role) throws Exception {
        var roleEntity = new RoleEntity(role);
        if (roleEntity.isValid()) {
            var createdRole = repository.save(roleEntity);
            return createdRole.orElse(null);
        } else {
            return null;
        }
    }

    public Role updateRole(Role role) throws Exception {
        var roleEntity = new RoleEntity(role);
        if (roleEntity.isValid()) {
            var updatedCompany = repository.update(roleEntity);
            return updatedCompany.orElse(null);
        } else {
            return null;
        }
    }

    public Role getRoleByName(String name) throws Exception {
        return repository.getByName(name).orElse(null);
    }
}
