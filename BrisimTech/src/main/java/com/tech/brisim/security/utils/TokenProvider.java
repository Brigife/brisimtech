package com.tech.brisim.security.utils;

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
//// ✅ 1. OtpEntity.java
//package com.tech.brisim.security.otp;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "otp_codes")
//public class OtpEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String username;
//    private String hashedOtp;
//    private LocalDateTime expiresAt;
//    private boolean verified;
//
//    public Long getId() { return id; }
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//    public String getHashedOtp() { return hashedOtp; }
//    public void setHashedOtp(String hashedOtp) { this.hashedOtp = hashedOtp; }
//    public LocalDateTime getExpiresAt() { return expiresAt; }
//    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
//    public boolean isVerified() { return verified; }
//    public void setVerified(boolean verified) { this.verified = verified; }
//}
//
//
/// / ✅ 2. OtpRepository.java
//package com.tech.brisim.security.otp;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.Optional;
//
//public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
//    Optional<OtpEntity> findTopByUsernameOrderByExpiresAtDesc(String username);
//}
//
//
//// ✅ 3. OtpService.java
//package com.tech.brisim.security.otp;
//
//import com.tech.brisim.notreportmgt.notfkns.mail.EmailService;
//import com.tech.brisim.notreportmgt.notfkns.mail.SmsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Random;
//
//@Service
//public class OtpService {
//
//    @Autowired private OtpRepository otpRepository;
//    @Autowired private EmailService emailService;
//    @Autowired private SmsService smsService;
//    @Autowired private PasswordEncoder passwordEncoder;
//
//    private static final int OTP_LENGTH = 6;
//    private static final int EXPIRY_MINUTES = 5;
//
//    public void generateAndSendOtp(String username, String deliveryMethod) {
//        String otp = String.format("%06d", new Random().nextInt(999999));
//        String hashedOtp = passwordEncoder.encode(otp);
//
//        OtpEntity entity = new OtpEntity();
//        entity.setUsername(username);
//        entity.setHashedOtp(hashedOtp);
//        entity.setExpiresAt(LocalDateTime.now().plusMinutes(EXPIRY_MINUTES));
//        entity.setVerified(false);
//        otpRepository.save(entity);
//
//        String message = "Your verification code is: " + otp + ". It expires in " + EXPIRY_MINUTES + " minutes.";
//        if ("email".equalsIgnoreCase(deliveryMethod)) {
//            emailService.sendSimpleMessage(username, "OTP Verification", message);
//        } else if ("sms".equalsIgnoreCase(deliveryMethod)) {
//            smsService.sendSMS(username, message);
//        }
//    }
//
//    public boolean verifyOtp(String username, String otp) {
//        return otpRepository.findTopByUsernameOrderByExpiresAtDesc(username)
//            .filter(o -> !o.isVerified() && o.getExpiresAt().isAfter(LocalDateTime.now()))
//            .filter(o -> passwordEncoder.matches(otp, o.getHashedOtp()))
//            .map(o -> {
//                o.setVerified(true);
//                otpRepository.save(o);
//                return true;
//            })
//            .orElse(false);
//    }
//}
//
//
//// ✅ 4. TokenService.java
//package com.tech.brisim.security.svces;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Service;
//import java.util.Date;
//
//@Service
//public class TokenService {
//    private static final String SECRET_KEY = "replace-with-a-secure-key";
//    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//            .setSubject(username)
//            .setIssuedAt(new Date())
//            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//            .compact();
//    }
//}
//
//
//// ✅ 5. RegistrationController.java
//package com.tech.brisim.security.controllers;
//
//import com.tech.brisim.security.models.UserEntity;
//import com.tech.brisim.security.repos.UserRepository;
//import com.tech.brisim.security.otp.OtpService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class RegistrationController {
//
//    @Autowired private UserRepository userRepository;
//    @Autowired private PasswordEncoder passwordEncoder;
//    @Autowired private OtpService otpService;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestParam String username,
//                                      @RequestParam String password,
//                                      @RequestParam String deliveryMethod) {
//        if (userRepository.existsByUsername(username)) {
//            return ResponseEntity.badRequest().body("Username already exists");
//        }
//
//        UserEntity user = new UserEntity();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setEnabled(false);
//        userRepository.save(user);
//
//        otpService.generateAndSendOtp(username, deliveryMethod);
//        return ResponseEntity.ok("User registered. OTP sent.");
//    }
//}
//
//
//// ✅ 6. LoginController.java
//package com.tech.brisim.security.controllers;
//
//import com.tech.brisim.security.otp.OtpService;
//import com.tech.brisim.security.repos.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class LoginController {
//
//    @Autowired private AuthenticationManager authManager;
//    @Autowired private UserRepository userRepository;
//    @Autowired private OtpService otpService;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String username,
//                                   @RequestParam String password,
//                                   @RequestParam String method) {
//        try {
//            Authentication auth = authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password));
//            otpService.generateAndSendOtp(username, method);
//            return ResponseEntity.ok("Password verified. OTP sent.");
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body("Invalid credentials.");
//        }
//    }
//}
//
//
//// ✅ 7. OtpController.java
//package com.tech.brisim.security.otp;
//
//import com.tech.brisim.security.models.UserEntity;
//import com.tech.brisim.security.repos.UserRepository;
//import com.tech.brisim.security.svces.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/otp")
//public class OtpController {
//
//    @Autowired private OtpService otpService;
//    @Autowired private UserRepository userRepository;
//    @Autowired private TokenService tokenService;
//
//    @PostMapping("/verify")
//    public ResponseEntity<?> verifyOtp(@RequestParam String username, @RequestParam String otp) {
//        boolean verified = otpService.verifyOtp(username, otp);
//
//        if (verified) {
//            UserEntity user = userRepository.findByUsername(username).orElseThrow();
//            user.setEnabled(true);
//            userRepository.save(user);
//            return ResponseEntity.ok("Account verified. You can now log in.");
//        } else {
//            return ResponseEntity.badRequest().body("Invalid or expired OTP.");
//        }
//    }
//
//    @PostMapping("/verify-login")
//    public ResponseEntity<?> verifyLoginOtp(@RequestParam String username, @RequestParam String otp) {
//        boolean verified = otpService.verifyOtp(username, otp);
//
//        if (verified) {
//            String jwtToken = tokenService.generateToken(username);
//            return ResponseEntity.ok(Map.of("token", jwtToken));
//        } else {
//            return ResponseEntity.badRequest().body("Invalid or expired OTP.");
//        }
//    }
//}
