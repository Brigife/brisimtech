package com.tech.brisim.servicemgt.services;

import com.tech.brisim.servicemgt.dtos.ServiceDTO;
import com.tech.brisim.servicemgt.exceptions.ResourceNotFoundException;
import com.tech.brisim.servicemgt.mapper.ServiceMapper;
import com.tech.brisim.servicemgt.models.Svce;
import com.tech.brisim.servicemgt.models.ServiceStatus;
import com.tech.brisim.servicemgt.repository.ServiceRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceService(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    // Create Svce with default status PENDING
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Svce svce = serviceMapper.toEntity(serviceDTO);
        svce.setStatus(ServiceStatus.PENDING); // Default status
        return serviceMapper.toDto(serviceRepository.save(svce));
    }

    // Retrieve all services
    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    // Update service
    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        Svce svce = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Svce not found"));

        svce.setName(serviceDTO.getName());
        svce.setDescription(serviceDTO.getDescription());
        svce.setPrice(serviceDTO.getPrice());

        // Update status based on some condition (can be dynamic or from DTO)
        if (serviceDTO.getStatus() != null) {
            svce.setStatus(ServiceStatus.valueOf(serviceDTO.getStatus()));
        }

        return serviceMapper.toDto(serviceRepository.save(svce));
    }

    // Change service status (manually or via some condition)
    public ServiceDTO updateServiceStatus(Long id, String status) {
        Svce svce = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Svce not found"));

        // Set the status, making sure it's a valid enum
        try {
            ServiceStatus newStatus = ServiceStatus.valueOf(status.toUpperCase());
            svce.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid status: " + status);
        }

        return serviceMapper.toDto(serviceRepository.save(svce));
    }

    // Delete service by ID
    public void deleteService(Long id) {
        Svce svce = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Svce not found"));
        serviceRepository.delete(svce);
    }

    // Retrieve services by specific status (e.g., ACTIVE, COMPLETED)
    public List<ServiceDTO> getServicesByStatus(String status) {
        return serviceRepository.findAll().stream()
                .filter(service -> service.getStatus().name().equalsIgnoreCase(status))
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }
}
