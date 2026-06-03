package com.foodrescue.dto;

import com.foodrescue.entity.DonationStatus;
import lombok.Data;

@Data
public class FoodDonationDTO {

    private String foodName;

    private String quantity;

    private String description;

    private DonationStatus status;

    private Double latitude;

    private Double longitude;
}