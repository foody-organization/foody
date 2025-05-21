package com.project.foody.restaurant.service;

import com.project.foody.restaurant.dto.FacilityDto;
import com.project.foody.restaurant.entity.Facility;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.repository.FacilityRepository;
import com.project.foody.restaurant.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final RestaurantRepository restaurantRepository;

    public Long addFacility(Long restaurantId, FacilityDto.Request dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        Facility facility = Facility.builder()
                .name(dto.getName())
                .restaurant(restaurant)
                .build();

        return facilityRepository.save(facility).getId();
    }
}
