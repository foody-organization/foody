package com.project.foody.restaurant.repository;

import com.project.foody.restaurant.dto.RestaurantSimpleDto;
import com.project.foody.restaurant.entity.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.restaurantFacilities rf " +
            "LEFT JOIN FETCH rf.facility " +
            "LEFT JOIN FETCH r.images " +
            "LEFT JOIN FETCH r.menus " +
            "WHERE r.id = :id")
    Optional<Restaurant> findByIdWithAll(@Param("id") Long id);

    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.restaurantFacilities rf " +
            "LEFT JOIN FETCH rf.facility " +
            "LEFT JOIN FETCH r.images " +
            "LEFT JOIN FETCH r.menus")
    List<Restaurant> findAllWithAll();


    // ✅ EntityGraph 방식 사용
    @EntityGraph(attributePaths = {"restaurantFacilities.facility", "images", "menus"})
    Optional<Restaurant> findById(Long id);
}
