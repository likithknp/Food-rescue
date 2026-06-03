package com.foodrescue.repository;

import com.foodrescue.entity.NGO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NGORepository extends JpaRepository<NGO, Long> {
}