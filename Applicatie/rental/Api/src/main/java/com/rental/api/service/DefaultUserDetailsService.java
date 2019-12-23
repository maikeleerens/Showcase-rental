package com.rental.api.service;

import com.rental.api.models.CustomUserDetails;
import com.rental.api.viewmodels.user.UserViewModel;
import com.rental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            var user = service.getUserByEmail(email);
            return new CustomUserDetails(new UserViewModel(user));
        } catch (Exception ex) {
            throw new UsernameNotFoundException("No user found");
        }

    }
}
