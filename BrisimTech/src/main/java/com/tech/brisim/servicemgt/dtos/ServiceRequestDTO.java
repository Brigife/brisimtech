package com.tech.brisim.servicemgt.dtos;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ServiceRequestDTO {
    private Long id;
    private Long serviceId;
    private Long customerId;
    private String status;
    private LocalDateTime requestDate;

    // Constructors, Getters, Setters
}
