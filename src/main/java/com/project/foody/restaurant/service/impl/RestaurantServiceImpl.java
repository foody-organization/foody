package com.project.foody.restaurant.service.impl;

import com.project.foody.restaurant.dto.RestaurantDto;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.repository.RestaurantRepository;
import com.project.foody.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Override
    public Long create(RestaurantDto.Request dto) {
        Restaurant entity = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .build();
        return repository.save(entity).getId();
    }

    @Override
    public void update(Long id, RestaurantDto.Request dto) {
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        restaurant.update(dto.getName(), dto.getAddress());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RestaurantDto.Response findById(Long id) {
        return repository.findById(id)
                .map(r -> RestaurantDto.Response.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .address(r.getAddress())
                        .createDate(r.getCreateDate())
                        .updateDate(r.getUpdateDate())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
    }

    @Override
    public List<RestaurantDto.Response> findAll() {
        return repository.findAll().stream()
                .map(r -> RestaurantDto.Response.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .address(r.getAddress())
                        .createDate(r.getCreateDate())
                        .updateDate(r.getUpdateDate())
                        .build())
                .collect(Collectors.toList());
    }
}
