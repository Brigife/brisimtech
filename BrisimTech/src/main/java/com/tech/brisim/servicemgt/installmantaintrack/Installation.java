package com.tech.brisim.servicemgt.installmantaintrack;

import com.tech.brisim.cusmangt.serreqmgt.ServiceRequest;
import jakarta.persistence.*;

@Entity
public class Installation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ServiceRequest serviceRequest;

    private String installationDate;
    private String installerDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }

    public String getInstallerDetails() {
        return installerDetails;
    }

    public void setInstallerDetails(String installerDetails) {
        this.installerDetails = installerDetails;
    }
}
