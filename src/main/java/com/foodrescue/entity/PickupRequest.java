package com.foodrescue.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pickup_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickupRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pickup_req_seq_gen")
    @SequenceGenerator(
            name = "pickup_req_seq_gen",
            sequenceName = "pickup_requests_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donation_id")
    private FoodDonation donation;

    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private NGO ngo;

    private String status;
}