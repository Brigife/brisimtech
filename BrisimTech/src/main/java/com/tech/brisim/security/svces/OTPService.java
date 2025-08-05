package com.tech.brisim.security.svces;

import com.tech.brisim.security.entity.OTP;
import com.tech.brisim.security.entity.User;
import com.tech.brisim.security.repo.OTPRepository;
import com.tech.brisim.security.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;

@Service
public class OTPService {

    private static final int OTP_LENGTH = 6;
    private static final int EXPIRATION_TIME_IN_MINUTES = 5;

    private final OTPRepository otpRepository;
    private final UserRepository userRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    public OTPService(OTPRepository otpRepository, UserRepository userRepository) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
    }

    /**
     * Generates and stores a new OTP for the given username.
     */
    public OTP generateOTP(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        String otpCode = generateSecureOTP();
        Date expirationTime = new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MINUTES * 60 * 1000);

        OTP otp = new OTP();
        otp.setUser(user);
        otp.setOtpCode(otpCode);
        otp.setExpirationTime(expirationTime);
        otp.setVerified(false);

        // Optional: Remove any previous OTP for the user
        otpRepository.deleteByUser(user);

        return otpRepository.save(otp);
    }

    /**
     * Validates the OTP entered by the user.
     */
    public boolean validateOTP(String username, String inputCode) {
        Optional<OTP> otpOptional = Optional.ofNullable(otpRepository.findByUsername(username));

        if (otpOptional.isEmpty()) return false;

        OTP otp = otpOptional.get();

        if (otp.getExpirationTime().before(new Date())) {
            otpRepository.delete(otp); // Clean up expired OTP
            return false;
        }

        if (!otp.getOtpCode().equals(inputCode)) {
            return false;
        }

        otp.setVerified(true);
        otpRepository.save(otp);
        otpRepository.delete(otp); // Invalidate OTP after use (one-time)

        return true;
    }

    /**
     * Generates a secure 6-digit numeric OTP.
     */
    private String generateSecureOTP() {
        int min = (int) Math.pow(10, OTP_LENGTH - 1);
        int max = (int) Math.pow(10, OTP_LENGTH) - 1;
        int otp = min + secureRandom.nextInt(max - min + 1);
        return String.valueOf(otp);
    }
}
