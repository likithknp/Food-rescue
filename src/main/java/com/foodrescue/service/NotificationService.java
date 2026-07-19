package com.foodrescue.service;

import com.foodrescue.entity.EmergencyRequest;
import com.foodrescue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Stubbed NotificationService.
 *
 * The project is currently running without mail support (option B). Keep this service as a
 * no-op stub so other code can call it without pulling in mail dependencies or requiring
 * additional runtime configuration. If you enable mail later, reintroduce JavaMailSender
 * and the mail sending logic.
 */
@Service
public class NotificationService {

    @Autowired(required = false)
    private UserRepository userRepository;

    public void broadcastEmergency(EmergencyRequest request) {
        int users = 0;
        try {
            if (userRepository != null) {
                users = (int) userRepository.count();
            }
        } catch (Exception ignored) {
        }
        System.out.println("[NotificationService] mail disabled; would notify " + users + " users. Request id: " + (request != null ? request.getId() : "n/a"));
    }
}

