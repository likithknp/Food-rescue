package com.foodrescue.controller;

import com.foodrescue.entity.FoodDonation;
import com.foodrescue.service.FoodDonationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class FoodDonationController {

    private final FoodDonationService service;

    public FoodDonationController(FoodDonationService service) {
        this.service = service;
    }

    @PostMapping
    public FoodDonation createDonation(@RequestBody FoodDonation donation) {
        return service.saveDonation(donation);
    }

    @GetMapping
    public List<FoodDonation> getAllDonations() {
        return service.getAllDonations();
    }

    @GetMapping("/available")
    public List<FoodDonation> getAvailableFoods() {
        return service.getAvailableDonations();
    }
}