package com.foodrescue.repository;

import com.foodrescue.entity.FoodDonation;
import com.foodrescue.entity.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodDonationRepository extends JpaRepository<FoodDonation, Long> {

    List<FoodDonation> findByStatus(DonationStatus status);

    long countByStatus(DonationStatus status);
}