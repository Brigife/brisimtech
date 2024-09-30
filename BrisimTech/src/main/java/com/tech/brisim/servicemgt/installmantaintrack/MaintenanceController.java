package com.tech.brisim.servicemgt.installmantaintrack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    // Endpoint to track and log maintenance
    @PostMapping("/track")
    public ResponseEntity<Mantainance> trackMaintenance(@RequestBody Mantainance maintenance) {
        Mantainance trackedMaintenance = maintenanceService.trackMaintenance(maintenance);
        return new ResponseEntity<>(trackedMaintenance, HttpStatus.CREATED);
    }

    // Endpoint to get maintenance records by service request ID
    @GetMapping("/by-request/{requestId}")
    public ResponseEntity<List<Mantainance>> getMaintenanceByServiceRequest(@PathVariable Long requestId) {
        List<Mantainance> maintenanceList = maintenanceService.getMaintenanceByServiceRequest(requestId);

        if (maintenanceList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no records found
        }
        return new ResponseEntity<>(maintenanceList, HttpStatus.OK); // Return 200 with maintenance records
    }
}