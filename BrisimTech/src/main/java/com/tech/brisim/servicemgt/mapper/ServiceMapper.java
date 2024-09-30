package com.tech.brisim.servicemgt.mapper;

import com.tech.brisim.servicemgt.dtos.ServiceDTO;
import com.tech.brisim.servicemgt.models.Svce;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public ServiceDTO toDto(Svce svce) {
        ServiceDTO dto = new ServiceDTO();
        dto.setId(svce.getId());
        dto.setName(svce.getName());
        dto.setDescription(svce.getDescription());
        dto.setPrice(svce.getPrice());
        dto.setStatus(svce.getStatus().name());
        return dto;
    }

    public Svce toEntity(ServiceDTO dto) {
        Svce svce = new Svce();
        svce.setName(dto.getName());
        svce.setDescription(dto.getDescription());
        svce.setPrice(dto.getPrice());
        return svce;
    }
}
