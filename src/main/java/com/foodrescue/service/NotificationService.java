package com.foodrescue.service;

import com.foodrescue.entity.EmergencyRequest;
import com.foodrescue.entity.User;
import com.foodrescue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired(required = false)
    private UserRepository userRepository;

    @Value("${mail.enabled:false}")
    private boolean mailEnabled;

    /**
     * Broadcast an emergency request to all users via email when mail is enabled and
     * JavaMailSender is available. Falls back to a console log when mail is disabled.
     */
    public void broadcastEmergency(EmergencyRequest request) {
        if (mailEnabled && mailSender != null && userRepository != null) {
            try {
                List<User> users = userRepository.findAll();
                int sent = 0;
                for (User u : users) {
                    if (u.getEmail() == null || u.getEmail().isBlank()) continue;
                    try {
                        SimpleMailMessage msg = new SimpleMailMessage();
                        msg.setTo(u.getEmail());
                        msg.setFrom("noreply@foodrescue.example");
                        msg.setSubject("Emergency request near you");
                        StringBuilder body = new StringBuilder();
                        body.append("An emergency request has been posted:\n\n");
                        if (request.getReason() != null) body.append("Reason: ").append(request.getReason()).append("\n");
                        if (request.getContactNumber() != null) body.append("Contact: ").append(request.getContactNumber()).append("\n");
                        if (request.getNotes() != null) body.append("Notes: ").append(request.getNotes()).append("\n");
                        body.append("\nPlease open the Food Rescue app to respond.");
                        msg.setText(body.toString());
                        mailSender.send(msg);
                        sent++;
                    } catch (Exception ex) {
                        System.err.println("[NotificationService] failed to send email to " + u.getEmail() + ": " + ex.getMessage());
                    }
                }
                System.out.println("[NotificationService] broadcast completed. Emails sent: " + sent);
            } catch (Exception ex) {
                System.err.println("[NotificationService] broadcast failed: " + ex.getMessage());
            }
        } else {
            int users = 0;
            try {
                if (userRepository != null) users = (int) userRepository.count();
            } catch (Exception ignored) {
            }
            System.out.println("[NotificationService] mail disabled or not configured; would notify " + users + " users. Request id: " + (request != null ? request.getId() : "n/a"));
        }
    }
}

