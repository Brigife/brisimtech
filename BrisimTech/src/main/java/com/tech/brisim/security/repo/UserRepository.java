package com.tech.brisim.security.repo;



import com.tech.brisim.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username); // Find user by username

    boolean existsByUsername(String username); // Check if user exists by username
}

