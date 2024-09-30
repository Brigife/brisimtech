package com.tech.brisim.servicemgt.installmantaintrack;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstallationService {

    private final InstallationRepository installationRepository;

    public InstallationService(InstallationRepository installationRepository) {
        this.installationRepository = installationRepository;
    }

    public Installation trackInstallation(Installation installation) {
        return installationRepository.save(installation);
    }

    public List<Installation> getInstallationsByServiceRequest(Long requestId) {
        return installationRepository.findByServiceRequestId(requestId);
    }
}
