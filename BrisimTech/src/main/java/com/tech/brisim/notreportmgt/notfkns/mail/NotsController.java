package com.tech.brisim.notreportmgt.notfkns.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotsController {
    @Autowired
    private NotsService notsService;

    // POST endpoint to send a notification
    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(
            @RequestParam String message,
            @RequestParam String recipient,
            @RequestParam String channel) {

        try {
            notsService.sendNotification(message, recipient, channel);
            return ResponseEntity.ok("Notification sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending notification: " + e.getMessage());
        }
    }
}


