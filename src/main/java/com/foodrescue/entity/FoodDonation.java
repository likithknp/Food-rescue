package com.foodrescue.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDonation {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "food_seq"
    )
    @SequenceGenerator(
            name = "food_seq",
            sequenceName = "FOOD_DONATIONS_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String foodName;

    private String quantity;

    private String description;

    private String imageUrl;

    private LocalDateTime expiryTime;

    @Enumerated(EnumType.STRING)
    private DonationStatus status;

    @Column(length = 2000)
    private String pickupLocation;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = true)
    private User donor;
}