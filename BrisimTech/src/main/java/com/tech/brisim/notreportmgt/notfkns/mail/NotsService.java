package com.tech.brisim.notreportmgt.notfkns.mail;





import com.tech.brisim.notreportmgt.notfkns.nots.Notification;
import com.tech.brisim.notreportmgt.notfkns.nots.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotsService {

    @Autowired

    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

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

    // Sending logic for email, SMS, etc.
    private boolean sendThroughChannel(String message, String recipient, String channel) {
        switch (channel.toUpperCase()) {
            case "EMAIL":
                // Use the EmailService
                return emailService.sendEmail(recipient, "Notification", message);

            case "SMS":
                // Use the SmsService
                return smsService.sendSms(recipient, message);

            default:
                return false; // Unsupported channel
        }
    }
}

