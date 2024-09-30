package com.tech.brisim.notreportmgt.notfkns.mail;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Data
public class SmsService {

    // Injected Twilio credentials from application properties
    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.phone_number}")
    private String fromPhoneNumber;

    // Twilio initialization moved to PostConstruct method
    @PostConstruct
    public void init() {
        if (accountSid == null || authToken == null || fromPhoneNumber == null) {
            throw new IllegalArgumentException("Twilio credentials not set.");
        }

        Twilio.init(accountSid, authToken);
    }

    // Method to send an SMS
    public boolean sendSms(String to, String text) {
        try {
            Message.creator(new PhoneNumber(to), new PhoneNumber(fromPhoneNumber), text).create();
            return true; // SMS sent successfully
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Failed to send SMS
        }
    }
}
