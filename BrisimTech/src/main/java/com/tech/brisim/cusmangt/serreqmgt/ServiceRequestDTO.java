package com.tech.brisim.cusmangt.serreqmgt;

import lombok.Data;

@Data
public class ServiceRequestDTO {
    private Long id;
    private String customerId;
    private String requestType;
    private String description;
    private String status;
}