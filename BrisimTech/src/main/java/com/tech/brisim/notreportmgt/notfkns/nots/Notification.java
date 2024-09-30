package com.tech.brisim.notreportmgt.notfkns.nots;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String recipient;
    private String channel; // EMAIL, SMS, IN_APP
    private Date sentAt;
    private boolean delivered;

    // Getters and setters
}

