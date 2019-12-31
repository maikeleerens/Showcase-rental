package com.rental.api.viewmodels.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.api.viewmodels.role.RoleViewModel;
import com.rental.domain.entities.base.BaseEntity;
import com.rental.domain.interfaces.entities.Role;
import com.rental.domain.interfaces.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserViewModel extends BaseEntity implements User {

    //region Private attributes
    @JsonProperty("email")
    private String email;

    @JsonIgnore
    private String password;

    @JsonProperty("roles")
    private List<RoleViewModel> roles;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonIgnore
    private List<String> notifications;
    //endregion

    //region Constructors
    public UserViewModel() {
    }

    public UserViewModel(UUID userId) {
        setId(userId);
    }

    public UserViewModel(String email, String password, List<RoleViewModel> roles, String name, String address, String city) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public UserViewModel(User user) {
        setId(user.getId());
        email = user.getEmail();
        password = user.getPassword();
        roles = RoleViewModel.toRoleViewModels(user.getRoles());
        name = user.getName();
        address = user.getAddress();
        city = user.getCity();
        notifications = user.getNotifications();
    }
    //endregion

    //region Getters and setters
    @Override
    @JsonProperty("user_id")
    public UUID getId() {
        return super.getId();
    }

    @Override
    @JsonProperty("user_id")
    public void setId(UUID id) {
        super.setId(id);
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public List<? extends Role> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(List<? extends Role> roles) {
        this.roles = RoleViewModel.toRoleViewModels(roles);
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
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

    @Override
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @Override
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @Override
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    @JsonIgnore
    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    @JsonIgnore
    public void addNotification(String notification) {
        notifications.add(notification);
    }
    //endregion

    //region Public methods
    public static List<UserViewModel> toUserViewModels(List<? extends User> users) {
        List<UserViewModel> returnUserList = new ArrayList<>();

        for (var user:
                users) {
            returnUserList.add(new UserViewModel(user));
        }

        return returnUserList;
    }

    public static UserViewModel toUserViewModel(CreateUserViewModel model) {
        List<RoleViewModel> roleList = new ArrayList<>();
        roleList.add(new RoleViewModel("CUSTOMER"));
        return new UserViewModel(model.getEmail(), model.getPassword(), roleList, model.getName(), model.getAddress(), model.getCity());
    }

    public static UserViewModel toUserViewModel(UpdateUserViewModel model) {
        return new UserViewModel(model.getEmail(), model.getPassword(), null, model.getName(), model.getAddress(), model.getCity());
    }
    //endregion
}