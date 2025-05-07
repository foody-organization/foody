package com.project.foody.restaurant.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.foody.restaurant.dto.RestaurantDto;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.repository.RestaurantRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // 음식점 등록
    @Override
    public Long create(RestaurantDto.Request dto) {
        Restaurant restaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .description(dto.getDescription())
                .build();
        return restaurantRepository.save(restaurant).getId();
    }

    // 음식점 수정
    @Override
    public void update(Long id, RestaurantDto.Request dto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));
        
        Restaurant updated = Restaurant.builder()
                .id(restaurant.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .description(dto.getDescription())
                .build();

        restaurantRepository.save(updated);
    }

    // 음식점 단건 조회
    public RestaurantDto.Response findById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));

        return RestaurantDto.Response.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .description(restaurant.getDescription())
                .createDate(restaurant.getCreateDate())
                .updateDate(restaurant.getUpdateDate())
                .build();
    }

    // 음식점 삭제
    @Override
    public void delete(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));

        restaurantRepository.delete(restaurant);
    }
    
    // 음식점 전체 조회회
    @Override
    public List<RestaurantDto.Response> findAll() {
        return restaurantRepository.findAll().stream()
                .map(r -> RestaurantDto.Response.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .address(r.getAddress())
                        .description(r.getDescription())
                        .createDate(r.getCreateDate())
                        .updateDate(r.getUpdateDate())
                        .build())
                .collect(Collectors.toList());
    }

}
