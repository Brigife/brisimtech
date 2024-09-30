package com.tech.brisim.cusmangt.serreqmgt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    // Create a new service request
    @PostMapping
    public ResponseEntity<ServiceRequestDTO> createRequest(@RequestBody ServiceRequestDTO requestDto) {
        ServiceRequestDTO createdRequest = serviceRequestService.createRequest(requestDto);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // Retrieve a service request by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequestDTO> getRequestById(@PathVariable Long id) {
        Optional<ServiceRequestDTO> requestDto = serviceRequestService.getRequestById(id);
        return requestDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update a service request
    @PutMapping("/{id}")
    public ResponseEntity<ServiceRequestDTO> updateRequest(@PathVariable Long id, @RequestBody ServiceRequestDTO requestDto) {
        try {
            ServiceRequestDTO updatedRequest = serviceRequestService.updateRequest(id, requestDto);
            return ResponseEntity.ok(updatedRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a service request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        try {
            serviceRequestService.deleteRequest(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//    // Retrieve all service requests
//    @GetMapping
//    public ResponseEntity<List<ServiceRequestDTO>> getAllRequests() {
//        List<ServiceRequestDTO> requests = serviceRequestService.getAllRequests();
//        return ResponseEntity.ok(requests);
//    }
}
