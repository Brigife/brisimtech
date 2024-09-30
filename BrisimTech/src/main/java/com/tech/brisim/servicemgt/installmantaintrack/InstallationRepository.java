package com.tech.brisim.servicemgt.installmantaintrack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallationRepository extends JpaRepository<Installation, Long> {
    // Find installations by service request
    List<Installation> findByServiceRequestId(Long requestId);
}
