package com.tech.brisim.cusmangt.serreqmgt;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceRequestDTO {
    private Long id;
    private String requestType;
    private String description;
    private String status; // Status as String to allow for easier mapping from enum
    private Date createdAt; // Include createdAt if you want to expose it in the DTO
    //private Date requestDate; // Include requestDate if you want to expose it in the DTO
}
