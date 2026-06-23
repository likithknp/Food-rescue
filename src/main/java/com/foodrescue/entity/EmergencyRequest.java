package com.foodrescue.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class EmergencyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodType;

    private String quantity;

    private String location;

    private String reason;

    private String contactNumber;

    private String notes;

    private String status;

    private LocalDateTime createdAt;
}