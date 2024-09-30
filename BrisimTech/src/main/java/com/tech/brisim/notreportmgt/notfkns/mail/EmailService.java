package com.tech.brisim.notreportmgt.notfkns.mail;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Data
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            return true; // Indicating successful delivery
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Failed to send email
        }
    }
}
