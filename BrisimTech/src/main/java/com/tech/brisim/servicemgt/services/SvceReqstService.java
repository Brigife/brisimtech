package com.tech.brisim.servicemgt.services;

import com.tech.brisim.servicemgt.dtos.ServiceRequestDTO;
import com.tech.brisim.servicemgt.exceptions.ResourceNotFoundException;
import com.tech.brisim.servicemgt.mapper.ServiceRequestMapper;
import com.tech.brisim.servicemgt.models.Svce;
import com.tech.brisim.servicemgt.models.ServiceStatus;
import com.tech.brisim.servicemgt.models.SviceReqst;
import com.tech.brisim.servicemgt.repository.SvceReqstRepository;
import com.tech.brisim.servicemgt.repository.ServiceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class SvceReqstService {

    private final SvceReqstRepository requestRepository;
    private final ServiceRepository serviceRepository; // Repository for Svce entity
    private final ServiceRequestMapper requestMapper;

    public SvceReqstService(SvceReqstRepository requestRepository,
                            ServiceRepository serviceRepository,
                            ServiceRequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.serviceRepository = serviceRepository;
        this.requestMapper = requestMapper;
    }

    public SviceReqst createServiceRequest(Long serviceId, Long customerId) {
        Svce svce = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Svce not found with id: " + serviceId));

        SviceReqst request = new SviceReqst();
        request.setSvce(svce);
        request.setCustomerId(customerId);
        request.setStatus(ServiceStatus.PENDING);
        request.setRequestDate(LocalDateTime.now()); // Set request date if needed

        return requestRepository.save(request);
    }

    public List<ServiceRequestDTO> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(requestMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteServiceRequest(Long id) {
        SviceReqst request = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Svce request not found with id: " + id));
        requestRepository.delete(request);
    }
}
