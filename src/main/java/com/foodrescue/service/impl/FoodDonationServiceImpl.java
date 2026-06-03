package com.foodrescue.service.impl;

import com.foodrescue.entity.DonationStatus;
import com.foodrescue.entity.FoodDonation;
import com.foodrescue.repository.FoodDonationRepository;
import com.foodrescue.service.FoodDonationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodDonationServiceImpl implements FoodDonationService {

    private final FoodDonationRepository repository;

    public FoodDonationServiceImpl(FoodDonationRepository repository) {
        this.repository = repository;
    }

    @Override
    public FoodDonation saveDonation(FoodDonation donation) {
        return repository.save(donation);
    }

    @Override
    public List<FoodDonation> getAllDonations() {
        return repository.findAll();
    }

    @Override
    public List<FoodDonation> getAvailableDonations() {
        return repository.findByStatus(DonationStatus.AVAILABLE);
    }
}