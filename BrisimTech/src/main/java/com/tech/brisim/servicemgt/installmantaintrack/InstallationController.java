package com.tech.brisim.servicemgt.installmantaintrack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/installations")
public class InstallationController {

    private final InstallationService installationService;

    public InstallationController(InstallationService installationService) {
        this.installationService = installationService;
    }

    // Get all installations by service request ID
    @GetMapping("/by-request/{requestId}")
    public ResponseEntity<List<Installation>> getInstallationsByServiceRequest(@PathVariable Long requestId) {
        List<Installation> installations = installationService.getInstallationsByServiceRequest(requestId);

        if (installations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 if no installations are found
        }
        return new ResponseEntity<>(installations, HttpStatus.OK); // Return 200 with installations
    }
}
