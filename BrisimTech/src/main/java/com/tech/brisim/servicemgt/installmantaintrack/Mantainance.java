package com.tech.brisim.servicemgt.installmantaintrack;


import com.tech.brisim.cusmangt.serreqmgt.ServiceRequest;
import com.tech.brisim.servicemgt.models.SviceReqst;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Mantainance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServiceRequest serviceRequest;

    private String maintenanceDate;
    private String maintenanceDetails;

    // Getters and setters
}

