package com.project.foody.restaurant.repository;

import com.project.foody.restaurant.entity.RestaurantFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantFacilityRepository extends JpaRepository<RestaurantFacility, Long> {
}
