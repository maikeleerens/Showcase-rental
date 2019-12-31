package com.rental.api.viewmodels.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoleViewModel extends BaseEntity implements Role {

    //region Private attributes
    @JsonProperty("name")
    private String name;
    //endregion

    //region Constructors
    public RoleViewModel(Role role) {
        setId(role.getId());
        name = role.getName();
    }

    public RoleViewModel(String name) {
        this.name = name;
    }
    //endregion

    //region Getters and setters
    @Override
    @JsonProperty("role_id")
    public UUID getId() {
        return super.getId();
    }

    @Override
    @JsonProperty("role_id")
    public void setId(UUID id) {
        super.setId(id);
    }

    @Override
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Override
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    //endregion

    //region Public methods
    public static List<RoleViewModel> toRoleViewModels(List<? extends Role> roles) {
        List<RoleViewModel> returnRoleList = new ArrayList<>();

        for (var role:
                roles) {
            returnRoleList.add(new RoleViewModel(role));
        }
        return returnRoleList;
    }
    //endregion

}
