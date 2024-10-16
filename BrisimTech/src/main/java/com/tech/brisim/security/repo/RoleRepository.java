package com.tech.brisim.security.repo;

import com.tech.brisim.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name); // Find role by name (e.g., ROLE_USER, ROLE_ADMIN)
}
