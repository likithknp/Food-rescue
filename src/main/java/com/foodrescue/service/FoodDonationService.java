package com.foodrescue.service;

import com.foodrescue.entity.FoodDonation;

import java.util.List;

public interface FoodDonationService {

    FoodDonation saveDonation(FoodDonation donation);

    List<FoodDonation> getAllDonations();

    List<FoodDonation> getAvailableDonations();
}