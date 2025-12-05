package com.tech.brisim.security.controllers;

import com.tech.brisim.security.dto.OTPRequest;
import com.tech.brisim.security.svces.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/otp")
public class OTPController {

    private final OTPService otpService;

    @Autowired
    public OTPController(OTPService otpService) {
        this.otpService = otpService;
    }

    /**
     * Endpoint to generate and send OTP to user by username
     */
    @PostMapping("/send/{username}")
    public ResponseEntity<?> sendOTP(@PathVariable String username) {
        try {
            otpService.generateOTP(username);

            // ✅ Avoid returning actual OTP in production
            return ResponseEntity.ok(Map.of(
                    "message", "OTP sent successfully",
                    "username", username
            ));

            // 🔧 Optional for development only:
            // return ResponseEntity.ok(Map.of(
            //     "message", "OTP sent successfully",
            //     "username", username,
            //     "otpCode", generatedOtp.getOtpCode()
            // ));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Failed to send OTP",
                    "details", e.getMessage()
            ));
        }
    }

    /**
     * Endpoint to verify OTP
     */
    @PostMapping("/verify")
    public ResponseEntity<?> verifyOTP(@RequestBody OTPRequest otpRequest) {
        try {
            boolean isValid = otpService.validateOTP(
                    otpRequest.getUsername(),
                    otpRequest.getOtpCode()
            );

            if (isValid) {
                return ResponseEntity.ok(Map.of(
                        "message", "OTP verified successfully",
                        "username", otpRequest.getUsername()
                ));
            } else {
                return ResponseEntity.badRequest().body(Map.of(
                        "error", "Invalid or expired OTP"
                ));
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Verification failed",
                    "details", e.getMessage()
            ));
        }
    }
}
