package com.tech.brisim.security.controllers;

import com.tech.brisim.security.dto.LoginRequest;
import com.tech.brisim.security.dto.RegisterRequest;
import com.tech.brisim.security.entity.User;
import com.tech.brisim.security.svces.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Create an authentication token using the provided username and password
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword());

        // Authenticate the user using the AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // Retrieve the authenticated user from the authentication object
        User user = (User) authentication.getPrincipal();

        // Generate a JWT token for the authenticated user
        String token = tokenService.generateToken(user.getUsername());

        // Return the generated token in the response
        return ResponseEntity.ok().body("Bearer " + token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // Implement user registration logic (e.g., save user to database)
        // For example, you can use userRepository to save the new user
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Implement logout logic (e.g., invalidate JWT token)
        return ResponseEntity.ok("Logged out successfully");
    }
}
