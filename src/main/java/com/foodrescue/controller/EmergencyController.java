package com.foodrescue.controller;

import com.foodrescue.entity.EmergencyRequest;
import com.foodrescue.repository.EmergencyRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.foodrescue.service.NotificationService;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/emergency")
@CrossOrigin("*")
public class EmergencyController {

    @Autowired
    private EmergencyRequestRepository repository;

    @Autowired(required = false)
    private NotificationService notificationService;


    @PostMapping
    public EmergencyRequest create(@RequestBody EmergencyRequest request) {

        request.setCreatedAt(LocalDateTime.now());
        request.setStatus("ACTIVE");

        EmergencyRequest saved = repository.save(request);

        if (notificationService != null) {
            new Thread(() -> {
                try {
                    notificationService.broadcastEmergency(saved);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        return saved;
    }

    @GetMapping
    public List<EmergencyRequest> getAllRequests() {
        return repository.findAll();
    }
}