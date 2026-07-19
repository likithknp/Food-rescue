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

                    if (u.getEmail() == null || u.getEmail().isBlank()) {
                        continue;
                    }

                    System.out.println("Preparing email for: " + u.getEmail());

                    try {
                        SimpleMailMessage msg = new SimpleMailMessage();

                        msg.setTo(u.getEmail());
                        msg.setFrom("foodrescue.notifications@gmail.com");
                        msg.setSubject("Emergency Request Near You");

                        StringBuilder body = new StringBuilder();
                        body.append("An emergency request has been posted.\n\n");

                        if (request.getReason() != null) {
                            body.append("Reason: ").append(request.getReason()).append("\n");
                        }

                        if (request.getContactNumber() != null) {
                            body.append("Contact Number: ").append(request.getContactNumber()).append("\n");
                        }

                        if (request.getNotes() != null) {
                            body.append("Notes: ").append(request.getNotes()).append("\n");
                        }

                        body.append("\nPlease open the Food Rescue application to respond.");

                        msg.setText(body.toString());

                        System.out.println("Calling mailSender.send()...");

                        mailSender.send(msg);

                        System.out.println("SUCCESS -> " + u.getEmail());

                    } catch (Exception ex) {
                        ex.printStackTrace();
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

