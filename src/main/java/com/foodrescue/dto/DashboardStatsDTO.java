package com.foodrescue.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDTO {

    private Long totalDonations;
    private Long availableFood;
    private Long totalUsers;
}