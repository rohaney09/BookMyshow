package com.example.BookMyShow.security.jwt;

import com.example.BookMyShow.User.model.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    // Secret key (minimum 256-bit for HS256)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity: 24 hours
    private final long expirationMs = 24 * 60 * 60 * 1000;

    public String generateToken(String email, Role role) {

        return Jwts.builder()
                .setSubject(email)
                .addClaims(Map.of("role", role.name()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
