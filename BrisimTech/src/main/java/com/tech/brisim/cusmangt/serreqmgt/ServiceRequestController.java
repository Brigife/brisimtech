package com.tech.brisim.cusmangt.serreqmgt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/service-requests")  // Base URL for service requests
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    // Endpoint to create a new service request
    @PostMapping("/service")
    public ResponseEntity<ServiceRequestDTO> createRequest(@RequestBody ServiceRequestDTO requestDto) {
        ServiceRequestDTO createdRequest = serviceRequestService.createRequest(requestDto);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);  // 201 Created
    }

    // Endpoint to get all service requests for a specific customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ServiceRequestDTO>> getAllRequestsByCustomer(@PathVariable String customerId) {
        List<ServiceRequestDTO> requests = serviceRequestService.getAllRequestsByCustomer(customerId);
        return new ResponseEntity<>(requests, HttpStatus.OK);  // 200 OK
    }

    // Endpoint to get a service request by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequestDTO> getRequestById(@PathVariable Long id) {
        Optional<ServiceRequestDTO> requestDto = serviceRequestService.getRequestById(id);
        return requestDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());  // 404 Not Found
    }

    // Endpoint to update a service request
    @PutMapping("/{id}")
    public ResponseEntity<ServiceRequestDTO> updateRequest(@PathVariable Long id, @RequestBody ServiceRequestDTO requestDto) {
        ServiceRequestDTO updatedRequest = serviceRequestService.updateRequest(id, requestDto);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);  // 200 OK
    }

    // Endpoint to delete a service request
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        serviceRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
