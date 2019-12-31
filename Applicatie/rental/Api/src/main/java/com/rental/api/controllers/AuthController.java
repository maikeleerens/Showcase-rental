package com.rental.api.controllers;

import com.rental.api.viewmodels.auth.AuthRequestResponseViewModel;
import com.rental.api.viewmodels.auth.AuthRequestViewModel;
import com.rental.services.authentication.DefaultUserDetailsService;
import com.rental.services.authentication.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = {"Auth management"})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully executed request"),
        @ApiResponse(code = 400, message = "Failed to execute request"),
        @ApiResponse(code = 401, message = "Unauthorized: Invalid username or password"),
        @ApiResponse(code = 403, message = "Unauthorized: No access to resource"),
        @ApiResponse(code = 404, message = "Resource not found")
})
public class AuthController {

    //region Private attributes
    private AuthenticationManager _authenticationManager;
    private DefaultUserDetailsService _userDetailsService;
    private JwtService _jwtService;
    //endregion

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, DefaultUserDetailsService userDetailsService, JwtService jwtService) {
        _authenticationManager = authenticationManager;
        _userDetailsService = userDetailsService;
        _jwtService = jwtService;
    }

    @ApiOperation(value = "Use email and password to obtain bearer token", response = String.class)
    @PostMapping
    public ResponseEntity authenticate(@RequestBody AuthRequestViewModel authRequest) {
        try {
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
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }
}
