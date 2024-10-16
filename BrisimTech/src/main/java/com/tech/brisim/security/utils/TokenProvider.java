package com.tech.brisim.security.utils;


import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    private final JwtUtil jwtUtil;

    public TokenProvider(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Generate access token
    public String generateAccessToken(String username) {
        return jwtUtil.generateToken(username);
    }

    // Validate access token
    public boolean validateAccessToken(String token, String username) {
        return jwtUtil.validateToken(token, username);
    }

    // Additional methods for refresh tokens can be added here
}

