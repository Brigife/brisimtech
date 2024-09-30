package com.tech.brisim.notreportmgt.notfkns.nots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Method to send a notification
    public void sendNotification(String message, String recipient, String channel) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setRecipient(recipient);
        notification.setChannel(channel);
        notification.setSentAt(new Date());
        notification.setDelivered(false);

        // Implement sending logic (Email, SMS, etc.)
        boolean isDelivered = sendThroughChannel(message, recipient, channel);
        notification.setDelivered(isDelivered);

        // Save notification to the database
        notificationRepository.save(notification);
    }

    // Mock method to handle channel-specific sending
    private boolean sendThroughChannel(String message, String recipient, String channel) {
        if (channel.equals("EMAIL")) {
            // Call email sending service
            return true;
        } else if (channel.equals("SMS")) {
            // Call SMS sending service
            return true;
        }
        return false;
    }
}

