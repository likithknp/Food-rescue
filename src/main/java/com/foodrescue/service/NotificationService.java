package com.foodrescue.service;

import com.foodrescue.entity.EmergencyRequest;
import com.foodrescue.entity.User;
import com.foodrescue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${mail.enabled:false}")
    private boolean mailEnabled;

    @Async
    public void broadcastEmergency(EmergencyRequest request) {
        List<User> users = userRepository.findAll();

        // If mail is not enabled or mailSender is not configured, just log and return
        if (!mailEnabled || mailSender == null) {
            System.out.println("[NotificationService] mail not enabled or mailSender missing. Would broadcast to " + users.size() + " users.");
            return;
        }

        String subject = "Emergency food request nearby";
        String text = String.format("An emergency request was posted:\n\nType: %s\nQuantity: %s\nLocation: %s\nReason: %s\n\nPlease respond if you can help.",
                request.getFoodType(), request.getQuantity(), request.getLocation(), request.getReason());

        for (User user : users) {
            if (user.getEmail() == null) continue;
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.getEmail());
                message.setSubject(subject);
                message.setText(text);
                mailSender.send(message);
            } catch (Exception ex) {
                System.err.println("Failed to send emergency email to " + user.getEmail() + ": " + ex.getMessage());
            }
        }
    }
}

