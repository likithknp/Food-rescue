package com.foodrescue.repository;

import com.foodrescue.entity.EmergencyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyRequestRepository
        extends JpaRepository<EmergencyRequest, Long> {
}