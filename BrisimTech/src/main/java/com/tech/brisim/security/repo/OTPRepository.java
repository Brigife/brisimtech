package com.tech.brisim.security.repo;

import com.tech.brisim.security.entity.OTP;
import com.tech.brisim.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {

    Optional<OTP> findByOtpCodeAndUser(String otpCode, User user); // Find OTP by code and user

    Optional<OTP> findFirstByUserOrderByExpirationTimeDesc(User user);
    OTP findByUsername(String username);
}

