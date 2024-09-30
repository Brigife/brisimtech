//package com.tech.brisim.servicemgt.utils;
//
//
//import com.tech.brisim.servicemgt.models.Svce;
//import com.tech.brisim.servicemgt.models.ServiceStatus;
//import com.tech.brisim.servicemgt.services.NotsService;
//import java.time.LocalDateTime;
//
//public class StatusUpdater {
//
//    private final NotsService notificationService;
//
//    public StatusUpdater(NotsService notificationService) {
//        this.notificationService = notificationService;
//    }
//
//    // Update status based on certain conditions (e.g., time, completion)
//    public Svce updateStatusBasedOnCondition(Svce service) {
//        LocalDateTime now = LocalDateTime.now();
//        // Sample condition: If the service has been pending for more than 7 days, mark it as "IN_PROGRESS"
//        if (service.getStatus() == ServiceStatus.PENDING && service.getCreatedAt().isBefore(now.minusDays(7))) {
//            service.setStatus(ServiceStatus.IN_PROGRESS);
//            sendStatusUpdateNotification(service);
//        }
//        // Add more conditions to update status here if needed.
//        return service;
//    }
//
//    // Manual status update method
//    public void updateStatus(Svce service, ServiceStatus newStatus) {
//        service.setStatus(newStatus);
//        sendStatusUpdateNotification(service);
//    }
//
//    // Send notfkns when the service status changes
//    private void sendStatusUpdateNotification(Svce service) {
//        String message = String.format("Svce '%s' has been updated to status: %s", service.getName(), service.getStatus().name());
//        notificationService.sendNotification(service.getCustomerEmail(), "Svce Status Update", message);
//    }
//}
//
