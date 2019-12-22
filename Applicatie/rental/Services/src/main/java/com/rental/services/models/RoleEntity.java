package com.rental.services.models;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Role;

public class RoleEntity extends BaseEntity implements Role {
    //region Private attributes

    private String name;

    //endregion

    //region Constructors
    public RoleEntity() {
    }

    public RoleEntity(Role role) {
        setId(role.getId());
        name = role.getName();
    }

    public RoleEntity(String name) {
        this.name = name;
    }
    //endregion

    //region Getters and setters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    //endregion

    //region Public methods
    public boolean isValid() {
        if ((getName() != null && !getName().isBlank())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Naam: " + getName();
    }
    //endregion
}
