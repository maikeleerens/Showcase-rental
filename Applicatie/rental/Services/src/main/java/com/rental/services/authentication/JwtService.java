package com.rental.services.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    //region Private attributes
    private String secretKey = "secret";
    //endregion

    public String extractUserName(String token) {
        return extractClaimFromToken(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T extractClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateJwtToken(UserDetails details) {
        Map<String, Object> claims = new HashMap<>();
        return createJwtToken(claims, details.getUsername());
    }

    private String createJwtToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public boolean validateJwtToken(String token, UserDetails details) {
        final String username = extractUserName(token);
        return (username.equals(details.getUsername()) && !isTokenExpired(token));
    }
}
