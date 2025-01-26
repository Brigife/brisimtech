package com.tech.brisim.security.svces;

import com.tech.brisim.security.entity.OTP;
import com.tech.brisim.security.entity.User;
import com.tech.brisim.security.repo.OTPRepository;
import com.tech.brisim.security.repo.UserRepository; // Import UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class OTPService {

    private final OTPRepository otpRepository;
    private final UserRepository userRepository; // Add user repository
    private static final int OTP_LENGTH = 6; // Length of OTP
    private static final int EXPIRATION_TIME_IN_MINUTES = 5; // OTP expiration time

    @Autowired
    public OTPService(OTPRepository otpRepository, UserRepository userRepository) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository; // Initialize user repository
    }

    // Generate OTP for a user
    public OTP generateOTP(String username) {
        User user = userRepository.findByUsername(username) // Find user by username
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        int otpCode = generateRandomOTP();
        OTP otp = new OTP();
        otp.setUser(user);
        otp.setOtpCode(String.valueOf(otpCode)); // Store OTP as String
        otp.setExpirationTime(new Date(System.currentTimeMillis() + (EXPIRATION_TIME_IN_MINUTES * 60 * 1000))); // Set expiration time
        otp.setVerified(false); // Set default verification status

        return otpRepository.save(otp);
    }

    // Validate the OTP
    public boolean validateOTP(String username, String otpCode) { // Change int to String
        OTP otp = otpRepository.findByUsername(username);
        if (otp == null || otp.getExpirationTime().before(new Date()) || !otp.getOtpCode().equals(otpCode)) {
            return false; // OTP not found, expired, or does not match
        }
        return true; // OTP is valid
    }

    // Generate a random OTP
    private int generateRandomOTP() {
        Random random = new Random();
        int min = (int) Math.pow(10, OTP_LENGTH - 1);
        int max = (int) Math.pow(10, OTP_LENGTH) - 1;
        return min + random.nextInt(max - min + 1);
    }
}
