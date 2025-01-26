package com.tech.brisim.security.controllers;

import com.tech.brisim.security.dto.OTPRequest;
import com.tech.brisim.security.entity.OTP;
import com.tech.brisim.security.svces.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation！」

@RestController
@RequestMapping("/otp")
public class OTPController {

    private final OTPService otpService;

    @Autowired
    public OTPController(OTPService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send/{username}")
    public ResponseEntity<?> sendOTP(@PathVariable String username) {
        OTP generatedOtp = otpService.generateOTP(username);
        return ResponseEntity.ok("OTP sent to " + username + ". Code: " + generatedOtp.getCode());
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOTP(@RequestBody OTPRequest otpRequest) {
        boolean isValid = otpService.validateOTP(otpRequest.getUsername(), otpRequest.getOtpCode()); // Use username from OTPRequest
        if (isValid) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }
    }

}
