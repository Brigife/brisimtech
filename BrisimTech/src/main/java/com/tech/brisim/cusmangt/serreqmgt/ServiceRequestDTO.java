package com.tech.brisim.cusmangt.serreqmgt;

import java.util.Date;

public class ServiceRequestDTO {
    private Long id;
    private String requestType;
    private String description;
    private String status; // Status as String to allow for easier mapping from enum
    private Date createdAt; // Include createdAt if you want to expose it in the DTO
    //private Date requestDate; // Include requestDate if you want to expose it in the DTO

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
