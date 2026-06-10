package com.foodrescue.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {

    private String userName;

    private Long totalDonations;

    private Long availableFood;

    private Long totalUsers;

    private Integer pointsEarned;

    private Integer pendingPickups;

    private Integer completedDonations;
}