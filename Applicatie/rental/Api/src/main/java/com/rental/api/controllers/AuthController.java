package com.rental.api.controllers;

import com.rental.services.authentication.DefaultUserDetailsService;
import com.rental.services.authentication.JwtService;
import com.rental.api.viewmodels.auth.AuthRequestResponseViewModel;
import com.rental.api.viewmodels.auth.AuthRequestViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager _authenticationManager;
    private DefaultUserDetailsService _userDetailsService;
    private JwtService _jwtService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, DefaultUserDetailsService userDetailsService, JwtService jwtService) {
        _authenticationManager = authenticationManager;
        _userDetailsService = userDetailsService;
        _jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity authenticate(@RequestBody AuthRequestViewModel authRequest) {
        try {
            _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        final UserDetails userDetails = _userDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String jwtBearerToken = _jwtService.generateJwtToken(userDetails);

        return ResponseEntity.status(HttpStatus.OK).body(new AuthRequestResponseViewModel(jwtBearerToken));
    }
}
