package com.tech.brisim.servicemgt.controllers;


import com.tech.brisim.servicemgt.dtos.ServiceDTO;
import com.tech.brisim.servicemgt.services.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // Create a new service
    @PostMapping("/new")
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO createdService = serviceService.createService(serviceDTO);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    // Get all services
    @GetMapping("/all")
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> services = serviceService.getAllServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    // Get services by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ServiceDTO>> getServicesByStatus(@PathVariable String status) {
        List<ServiceDTO> services = serviceService.getServicesByStatus(status);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    // Update a service
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        ServiceDTO updatedService = serviceService.updateService(id, serviceDTO);
        return new ResponseEntity<>(updatedService, HttpStatus.OK);
    }

    // Update the status of a service
    @PatchMapping("/{id}/status")
    public ResponseEntity<ServiceDTO> updateServiceStatus(@PathVariable Long id, @RequestParam String status) {
        ServiceDTO updatedService = serviceService.updateServiceStatus(id, status);
        return new ResponseEntity<>(updatedService, HttpStatus.OK);
    }

    // Delete a service
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

