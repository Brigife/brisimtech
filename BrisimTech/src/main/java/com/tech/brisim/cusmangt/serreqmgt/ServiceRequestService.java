package com.tech.brisim.cusmangt.serreqmgt;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    @Autowired
    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    // Create a new service request
    public ServiceRequestDTO createRequest(@NotNull ServiceRequestDTO requestDto) {
        ServiceRequest serviceRequest = new ServiceRequest();
        populateServiceRequest(serviceRequest, requestDto);
        serviceRequest.setCreatedAt(new Date()); // Set created timestamp

        ServiceRequest savedRequest = serviceRequestRepository.save(serviceRequest);
        return convertToDto(savedRequest);
    }

    // Retrieve a service request by ID
    public Optional<ServiceRequestDTO> getRequestById(Long id) {
        return serviceRequestRepository.findById(id)
                .map(this::convertToDto);
    }

    // Update a service request
    public ServiceRequestDTO updateRequest(Long id, @NotNull ServiceRequestDTO requestDto) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Request not found"));

        populateServiceRequest(serviceRequest, requestDto);
        updateStatus(serviceRequest, requestDto.getStatus());

        ServiceRequest updatedRequest = serviceRequestRepository.save(serviceRequest);
        return convertToDto(updatedRequest);
    }

    // Delete a service request
    public void deleteRequest(Long id) {
        serviceRequestRepository.deleteById(id);
    }

    // Convert ServiceRequest entity to ServiceRequestDTO
    private ServiceRequestDTO convertToDto(@NotNull ServiceRequest serviceRequest) {
        ServiceRequestDTO dto = new ServiceRequestDTO();
        dto.setId(serviceRequest.getId());
        dto.setRequestType(serviceRequest.getRequestType());
        dto.setDescription(serviceRequest.getDescription());
        dto.setStatus(serviceRequest.getStatus().name());
        dto.setCreatedAt(serviceRequest.getCreatedAt());
        return dto;
    }

    // Populate fields from DTO to ServiceRequest
    private void populateServiceRequest(ServiceRequest serviceRequest, ServiceRequestDTO requestDto) {
        serviceRequest.setRequestType(requestDto.getRequestType());
        serviceRequest.setDescription(requestDto.getDescription());
    }

    // Update the status of the service request based on DTO
    private void updateStatus(ServiceRequest serviceRequest, String status) {
        if (status != null) {
            try {
                serviceRequest.setStatus(ServiceRequest.RequestStatus.valueOf(status.toUpperCase())); // Handle case insensitivity
            } catch (IllegalArgumentException e) {
                serviceRequest.setStatus(ServiceRequest.RequestStatus.PENDING); // Default to PENDING if invalid status is provided
            }
        }
    }
}
