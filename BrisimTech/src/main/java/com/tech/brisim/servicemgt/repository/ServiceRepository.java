package com.tech.brisim.servicemgt.repository;

import com.tech.brisim.servicemgt.models.Svce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Svce, Long> {
    Optional<Svce> findByName(String name);
}