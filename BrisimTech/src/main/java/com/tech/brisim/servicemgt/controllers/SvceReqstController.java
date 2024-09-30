package com.tech.brisim.servicemgt.controllers;

import com.tech.brisim.servicemgt.dtos.ServiceRequestDTO;
import com.tech.brisim.servicemgt.models.SviceReqst;
import com.tech.brisim.servicemgt.repository.ServiceRepository;
import com.tech.brisim.servicemgt.services.SvceReqstService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class SvceReqstController {

    private final SvceReqstService svceReqstService;
    private final ServiceRepository serviceRepository;

    // Constructor to inject dependencies
    public SvceReqstController(SvceReqstService svceReqstService, ServiceRepository serviceRepository) {
        this.svceReqstService = svceReqstService;
        this.serviceRepository = serviceRepository;
    }

    /**
     * Create a new service request.
     *
     * @param serviceId the ID of the service
     * @param customerId the ID of the customer
     * @return ResponseEntity containing the created service request
     */
    @PostMapping
    public ResponseEntity<SviceReqst> createServiceRequest(
            @RequestParam Long serviceId,
            @RequestParam Long customerId) {
        // Check if serviceId exists before calling the service method
        if (!serviceRepository.existsById(serviceId)) {
            return ResponseEntity.badRequest().body(null); // Return error response
        }

        SviceReqst createdRequest = svceReqstService.createServiceRequest(serviceId, customerId);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    /**
     * Get all service requests.
     *
     * @return ResponseEntity containing the list of service requests
     */
    @GetMapping("/all")
    public ResponseEntity<List<ServiceRequestDTO>> getAllRequests() {
        List<ServiceRequestDTO> requests = svceReqstService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    /**
     * Delete a service request by ID.
     *
     * @param id the ID of the service request to delete
     * @return ResponseEntity with no content status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRequest(@PathVariable Long id) {
        svceReqstService.deleteServiceRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
