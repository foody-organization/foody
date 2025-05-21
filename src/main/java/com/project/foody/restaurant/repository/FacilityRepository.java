package com.project.foody.restaurant.repository;

import com.project.foody.restaurant.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    // 필요시: List<Facility> findByRestaurantId(Long restaurantId);
}
