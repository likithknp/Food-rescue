package com.foodrescue.controller;

import com.foodrescue.dto.DashboardStatsDTO;
import com.foodrescue.entity.DonationStatus;
import com.foodrescue.repository.FoodDonationRepository;
import com.foodrescue.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final FoodDonationRepository donationRepository;
    private final UserRepository userRepository;

    @GetMapping("/stats")
    public DashboardStatsDTO getStats() {

        long totalDonations = donationRepository.count();

        long availableFood =
                donationRepository.countByStatus(DonationStatus.AVAILABLE);

        long totalUsers =
                userRepository.count();

        return new DashboardStatsDTO(
                totalDonations,
                availableFood,
                totalUsers
        );
    }
}