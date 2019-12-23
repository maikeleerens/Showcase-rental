package com.rental.api.controllers;

import com.rental.api.service.DefaultUserDetailsService;
import com.rental.api.service.JwtService;
import com.rental.api.viewmodels.auth.AuthRequestResponseViewModel;
import com.rental.api.viewmodels.auth.AuthRequestViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity authenticate(@RequestBody AuthRequestViewModel authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String jwtBearerToken = jwtService.generateJwtToken(userDetails);

        return ResponseEntity.status(HttpStatus.OK).body(new AuthRequestResponseViewModel(jwtBearerToken));
    }
}
