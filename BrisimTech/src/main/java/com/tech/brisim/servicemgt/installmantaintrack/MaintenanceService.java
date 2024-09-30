package com.tech.brisim.servicemgt.installmantaintrack;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public Mantainance trackMaintenance(Mantainance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public List<Mantainance> getMaintenanceByServiceRequest(Long requestId) {
        return maintenanceRepository.findByServiceRequestId(requestId);
    }
}
