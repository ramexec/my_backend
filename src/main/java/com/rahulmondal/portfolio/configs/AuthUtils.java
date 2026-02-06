package com.rahulmondal.portfolio.configs;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.rahulmondal.portfolio.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class AuthUtils {

    @Value("${jwt.secret.key}")
    private String jwtSecrete;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecrete.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                   .subject(user.getUsername().toString())
                   .claim("userId", user.getId())
                   .claim("firstName", user.getFirstName())
                   .claim("secondName", user.getSecondName())
                   .claim("username", user.getUsername())
                   .claim("role", user.getRole())
                   .claim("email", user.getEmail())
                   .claim("phone", user.getPhone())
                   .claim("createdAt", user.getCreatedAt().toString())
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                   .signWith(getSecretKey())
                   .compact();
    }

    public String extractUsername(String jwt) {
        Claims claim = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        
        return claim.getSubject();
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isTokenValid'");
    }
}
