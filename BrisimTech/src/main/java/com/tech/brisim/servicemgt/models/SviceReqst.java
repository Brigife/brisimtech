package com.tech.brisim.servicemgt.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Svce getSvce() {
        return svce;
    }

    public void setSvce(Svce svce) {
        this.svce = svce;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
