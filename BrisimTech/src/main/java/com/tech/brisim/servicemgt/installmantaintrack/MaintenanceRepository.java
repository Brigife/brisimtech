package com.tech.brisim.servicemgt.installmantaintrack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Mantainance, Long> {
    List<Mantainance> findByServiceRequestId(Long serviceRequestId);
}
