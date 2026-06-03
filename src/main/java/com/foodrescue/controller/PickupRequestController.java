package com.foodrescue.controller;

import com.foodrescue.entity.PickupRequest;
import com.foodrescue.repository.PickupRequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class PickupRequestController {

    private final PickupRequestRepository repository;

    public PickupRequestController(PickupRequestRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public PickupRequest createRequest(@RequestBody PickupRequest request) {
        return repository.save(request);
    }

    @GetMapping
    public List<PickupRequest> getAllRequests() {
        return repository.findAll();
    }
}