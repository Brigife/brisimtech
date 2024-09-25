package com.tech.brisim.cusmangt.serreqmgt;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    // Method to retrieve all service requests for a specific customer
    public List<ServiceRequestDTO> getAllRequestsByCustomer(String customerId) {
        return serviceRequestRepository.findByCustomerId(customerId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Method to create a new service request
    public ServiceRequestDTO createRequest(@NotNull ServiceRequestDTO requestDto) {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setCustomerId(requestDto.getCustomerId());
        serviceRequest.setRequestType(requestDto.getRequestType());
        serviceRequest.setDescription(requestDto.getDescription());
        serviceRequest.setStatus(ServiceRequest.RequestStatus.PENDING);  // Set default status
        serviceRequest.setCreatedAt(new Date());  // Set created timestamp

        serviceRequest = serviceRequestRepository.save(serviceRequest);
        return convertToDto(serviceRequest);
    }

    // Method to retrieve a service request by ID
    public Optional<ServiceRequestDTO> getRequestById(Long id) {
        return serviceRequestRepository.findById(id)
                .map(this::convertToDto);
    }

    // Method to update a service request
    public ServiceRequestDTO updateRequest(Long id, @NotNull ServiceRequestDTO requestDto) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Request not found"));

        // Update fields
        serviceRequest.setRequestType(requestDto.getRequestType());
        serviceRequest.setDescription(requestDto.getDescription());

        // Update status based on the input from the DTO
        if (requestDto.getStatus() != null) {
            switch (requestDto.getStatus()) {
                case "IN_PROGRESS":
                    serviceRequest.setStatus(ServiceRequest.RequestStatus.IN_PROGRESS);
                    break;
                case "COMPLETED":
                    serviceRequest.setStatus(ServiceRequest.RequestStatus.COMPLETED);
                    break;
                case "CANCELED":
                    serviceRequest.setStatus(ServiceRequest.RequestStatus.CANCELED);
                    break;
                default:
                    serviceRequest.setStatus(ServiceRequest.RequestStatus.PENDING);
                    break;
            }
        }

        serviceRequest = serviceRequestRepository.save(serviceRequest);
        return convertToDto(serviceRequest);
    }

    // Method to delete a service request
    public void deleteRequest(Long id) {
        serviceRequestRepository.deleteById(id);
    }

    // Method to convert ServiceRequest entity to ServiceRequestDTO
    private ServiceRequestDTO convertToDto(@NotNull ServiceRequest serviceRequest) {
        ServiceRequestDTO dto = new ServiceRequestDTO();
        dto.setId(serviceRequest.getId());
        dto.setCustomerId(serviceRequest.getCustomerId());
        dto.setRequestType(serviceRequest.getRequestType());
        dto.setDescription(serviceRequest.getDescription());
        dto.setStatus(serviceRequest.getStatus().name());
        return dto;
    }
}
