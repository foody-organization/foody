package com.project.foody.restaurant.service;

import com.project.foody.restaurant.dto.RestaurantMenuDto;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.entity.RestaurantMenu;
import com.project.foody.restaurant.repository.RestaurantMenuRepository;
import com.project.foody.restaurant.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantMenuService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;

    public Long addMenu(Long restaurantId, RestaurantMenuDto.Request dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        RestaurantMenu menu = RestaurantMenu.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .restaurant(restaurant)
                .build();

        return restaurantMenuRepository.save(menu).getId();
    }
}