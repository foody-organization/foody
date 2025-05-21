package com.project.foody.restaurant.service;

import com.project.foody.restaurant.dto.RestaurantImageDto;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.entity.RestaurantImage;
import com.project.foody.restaurant.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantImageService {

    private final RestaurantRepository restaurantRepository;
    private final com.project.foody.repository.RestaurantImageRepository restaurantImageRepository;

    public Long uploadImage(Long restaurantId, RestaurantImageDto.Request dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        RestaurantImage image = RestaurantImage.builder()
                .imageUrl(dto.getImageUrl())
                .restaurant(restaurant)
                .build();

        return restaurantImageRepository.save(image).getId();
    }
}
