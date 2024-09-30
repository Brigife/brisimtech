package com.tech.brisim.servicemgt.mapper;

import com.tech.brisim.servicemgt.dtos.ServiceRequestDTO;
import com.tech.brisim.servicemgt.models.SviceReqst;
import org.springframework.stereotype.Component;

@Component
public class ServiceRequestMapper {

    public ServiceRequestDTO toDto(SviceReqst request) {
        ServiceRequestDTO dto = new ServiceRequestDTO();
        dto.setId(request.getId());
        dto.setServiceId(request.getSvce().getId());
        dto.setCustomerId(request.getCustomerId());
        dto.setStatus(request.getStatus().name());
        dto.setRequestDate(request.getRequestDate());
        return dto;
    }

    public SviceReqst toEntity(ServiceRequestDTO dto) {
        SviceReqst request = new SviceReqst();
        // Handle service lookup via ID
        request.setCustomerId(dto.getCustomerId());
        request.setRequestDate(dto.getRequestDate());
        return request;
    }
}
