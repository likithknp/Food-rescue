package com.foodrescue.controller;

import com.foodrescue.entity.EmergencyRequest;
import com.foodrescue.repository.EmergencyRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/emergency")
@CrossOrigin("*")
public class EmergencyController {

    @Autowired
    private EmergencyRequestRepository repository;

    @PostMapping
    public EmergencyRequest create(@RequestBody EmergencyRequest request) {

        request.setCreatedAt(LocalDateTime.now());
        request.setStatus("ACTIVE");

        return repository.save(request);
    }

    @GetMapping
    public List<EmergencyRequest> getAllRequests() {
        return repository.findAll();
    }
}