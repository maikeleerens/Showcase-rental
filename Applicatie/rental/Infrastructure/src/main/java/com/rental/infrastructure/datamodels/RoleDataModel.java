package com.rental.infrastructure.datamodels;

import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class RoleDataModel extends BaseEntity implements Role {

    //region Private attributes

    @Column(name = "name", nullable = false)
    private String name;

    //endregion

    //region Constructors
    public RoleDataModel() {
    }

    public RoleDataModel(Role role) {
        setId(role.getId());
        name = role.getName();
    }

    public RoleDataModel(String name) {
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


}
