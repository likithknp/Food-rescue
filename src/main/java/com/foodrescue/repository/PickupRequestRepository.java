package com.foodrescue.repository;

import com.foodrescue.entity.PickupRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupRequestRepository extends JpaRepository<PickupRequest, Long> {
}