package com.project.foody.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.project.foody.restaurant.dto.FacilityDto;
import com.project.foody.restaurant.dto.RestaurantDto;
import com.project.foody.restaurant.dto.RestaurantImageDto;
import com.project.foody.restaurant.dto.RestaurantMenuDto;
import com.project.foody.restaurant.entity.Facility;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.entity.RestaurantFacility;
import com.project.foody.restaurant.entity.RestaurantImage;
import com.project.foody.restaurant.entity.RestaurantMenu;
import com.project.foody.restaurant.repository.FacilityRepository;
import com.project.foody.restaurant.repository.RestaurantFacilityRepository;
import com.project.foody.restaurant.repository.RestaurantRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final FacilityRepository facilityRepository;
    private final RestaurantFacilityRepository restaurantFacilityRepository;

    @Override
    public Long create(RestaurantDto.Request dto) {
        Restaurant restaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .description(dto.getDescription())
                .build();

        if (dto.getImages() != null) {
            for (RestaurantImageDto.Request i : dto.getImages()) {
                RestaurantImage image = RestaurantImage.builder()
                        .imageUrl(i.getImageUrl())
                        .restaurant(restaurant)
                        .build();
                restaurant.addImage(image);
            }
        }

        if (dto.getMenus() != null) {
            for (RestaurantMenuDto.Request m : dto.getMenus()) {
                RestaurantMenu menu = RestaurantMenu.builder()
                        .name(m.getName())
                        .price(m.getPrice())
                        .description(m.getDescription())
                        .restaurant(restaurant)
                        .build();
                restaurant.addMenu(menu);
            }
        }

        return restaurantRepository.save(restaurant).getId();
    }

    @Override
    public void update(Long id, RestaurantDto.Request dto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));

        restaurant = Restaurant.builder()
                .id(restaurant.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .description(dto.getDescription())
                .build();

        restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantDto.Response findById(Long id) {
        Restaurant restaurant = restaurantRepository.findByIdWithAll(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));

        log.info("\u2705 restaurant: {}", restaurant);
        log.info("\u2705 facilities: {}", restaurant.getRestaurantFacilities());
        log.info("\u2705 images: {}", restaurant.getImages());
        log.info("\u2705 menus: {}", restaurant.getMenus());

        List<FacilityDto.Response> facilities = new ArrayList<>();
        if (restaurant.getRestaurantFacilities() != null) {
            facilities = restaurant.getRestaurantFacilities().stream()
                    .map(RestaurantFacility::getFacility)
                    .filter(Objects::nonNull)
                    .map(f -> FacilityDto.Response.builder()
                            .id(f.getId())
                            .name(f.getName())
                            .build())
                    .collect(Collectors.toList());
        }

        return RestaurantDto.Response.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .description(restaurant.getDescription())
                .createDate(restaurant.getCreateDate())
                .updateDate(restaurant.getUpdateDate())
                .facilities(facilities)
                .images(restaurant.getImages().stream()
                        .map(i -> RestaurantImageDto.Response.builder()
                                .id(i.getId())
                                .imageUrl(i.getImageUrl())
                                .build())
                        .collect(Collectors.toList()))
                .menus(restaurant.getMenus().stream()
                        .map(m -> RestaurantMenuDto.Response.builder()
                                .id(m.getId())
                                .name(m.getName())
                                .price(m.getPrice())
                                .description(m.getDescription())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public void delete(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));
        restaurantRepository.delete(restaurant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantDto.Response> findAll() {
        List<Restaurant> restaurants = restaurantRepository.findAllWithAll(); // fetch join 포함된 쿼리
        return restaurants.stream().map(restaurant -> RestaurantDto.Response.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .description(restaurant.getDescription())
                .createDate(restaurant.getCreateDate())
                .updateDate(restaurant.getUpdateDate())
                .facilities(restaurant.getRestaurantFacilities().stream()
                        .map(rf -> FacilityDto.Response.builder()
                                .id(rf.getFacility().getId())
                                .name(rf.getFacility().getName())
                                .build())
                        .collect(Collectors.toList()))
                .images(restaurant.getImages().stream()
                        .map(i -> RestaurantImageDto.Response.builder()
                                .id(i.getId())
                                .imageUrl(i.getImageUrl())
                                .build())
                        .collect(Collectors.toList()))
                .menus(restaurant.getMenus().stream()
                        .map(m -> RestaurantMenuDto.Response.builder()
                                .id(m.getId())
                                .name(m.getName())
                                .price(m.getPrice())
                                .description(m.getDescription())
                                .build())
                        .collect(Collectors.toList()))
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public void addFacilityToRestaurant(Long restaurantId, Long facilityId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new EntityNotFoundException("Facility not found"));

        // ✅ 양방향 연관관계 유지
        restaurant.addFacility(facility);
    }
}
