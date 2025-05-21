package com.project.foody.restaurant.repository;
import com.project.foody.restaurant.entity.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu, Long> {
}
