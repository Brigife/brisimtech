package com.tech.brisim.servicemgt.installmantaintrack;

import com.tech.brisim.cusmangt.serreqmgt.ServiceRequest;
import com.tech.brisim.servicemgt.models.SviceReqst;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Installation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServiceRequest serviceRequest;

    private String installationDate;
    private String installerDetails;

    // Getters and setters
}
