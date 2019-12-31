package com.rental.services.authentication;

import com.rental.services.UserService;
import com.rental.services.models.UserDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    //region Private attributes
    private UserService _service;
    //endregion

    @Autowired
    public DefaultUserDetailsService(UserService service) {
        _service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            var user = _service.getUserByEmail(email);
            if (user == null) throw new UsernameNotFoundException("No user found");
            return new UserDetailsEntity(user);
        } catch (Exception ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }
    }
}
