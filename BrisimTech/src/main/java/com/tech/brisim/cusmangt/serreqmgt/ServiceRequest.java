package com.tech.brisim.cusmangt.serreqmgt;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "service_requests")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerId;  // Foreign key or reference to Customer entity

    @Column(nullable = false)
    private String requestType;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        status = RequestStatus.PENDING;  // Default status
    }

    public enum RequestStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELED
    }
}