package com.project.foody.restaurant.repository;
import com.project.foody.restaurant.entity.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu, Long> {
}
