package com.tech.brisim.security.controllers;


import com.tech.brisim.security.dto.LoginRequest;
import com.tech.brisim.security.dto.RegisterRequest;
import com.tech.brisim.security.entity.User;
import com.tech.brisim.security.svces.CustomUserDetailsService;
import com.tech.brisim.security.svces.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(user.getUsername());

        return ResponseEntity.ok().body("Bearer " + token); // Return the JWT token
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

