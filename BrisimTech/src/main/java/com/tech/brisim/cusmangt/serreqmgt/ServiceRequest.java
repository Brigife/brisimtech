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
    private String requestType;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date", nullable = false)
    private Date requestDate;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        requestDate = new Date(); // Set the request date when the entity is created
        status = RequestStatus.PENDING; // Default status
    }

    public enum RequestStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELED
    }
}
