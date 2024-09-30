package com.tech.brisim.cusmangt.serreqmgt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    // JpaRepository already provides findById(Long id)
}
