package com.tech.brisim.cusmangt.feedbackreview;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerId;  // Foreign key or reference to Customer entity

    @Column(nullable = false)
    private Long serviceRequestId; // Reference to ServiceRequest

    @Column(nullable = false)
    private String comment;

    private Integer rating;  // Out of 5 or 10

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}