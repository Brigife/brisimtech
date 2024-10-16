package com.tech.brisim.security.dto;

import jakarta.validation.constraints.NotBlank;

public class OTPRequest {

    @NotBlank(message = "Username is required")
    private String username; // Add a field for the username

    @NotBlank(message = "OTP code is required")
    private String otpCode;

    // Getters and Setters
    public String getUsername() {
        return username; // Getter for username
    }

    public void setUsername(String username) {
        this.username = username; // Setter for username
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
}
