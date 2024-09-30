package com.tech.brisim.notreportmgt.notfkns.nots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SRequestService {

    @Autowired
    private NotificationService notificationService;

    public void updateRequestStatus(Long requestId, String newStatus) {
        // Update service request status logic here

        // Notify the customer
        notificationService.sendNotification(
                "Your service request status has been updated to " + newStatus,
                "customer@example.com", // Assume this is the customer's email
                "EMAIL"
        );
    }
}
