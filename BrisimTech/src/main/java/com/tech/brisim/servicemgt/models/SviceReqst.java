package com.tech.brisim.servicemgt.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "service_requests")
public class SviceReqst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_service_request_service"))
    private Svce svce;

    private Long customerId;

    @Enumerated(EnumType.STRING)
    private ServiceStatus status;

    @Column(name = "request_date", nullable = false, updatable = false)
    private LocalDateTime requestDate = LocalDateTime.now();

    // Constructors, Getters, Setters
}
