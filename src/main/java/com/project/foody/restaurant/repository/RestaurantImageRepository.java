package com.project.foody.repository;

import com.project.foody.restaurant.entity.RestaurantImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long> {
}
