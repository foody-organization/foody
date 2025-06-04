package com.project.foody.restaurant.service.impl;

import com.project.foody.restaurant.dto.RestaurantImageDto;
import com.project.foody.restaurant.entity.Menu;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.entity.RestaurantImage;
import com.project.foody.restaurant.repository.MenuRepository;
import com.project.foody.restaurant.repository.RestaurantImageRepository;
import com.project.foody.restaurant.repository.RestaurantRepository;
import com.project.foody.restaurant.service.RestaurantImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantImageServiceImpl implements RestaurantImageService {

    private final RestaurantImageRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Override
    public Long create(RestaurantImageDto.Request dto) {
        Restaurant restaurant = dto.getRestaurantId() != null
                ? restaurantRepository.findById(dto.getRestaurantId())
                .orElse(null) : null;

        Menu menu = dto.getMenuId() != null
                ? menuRepository.findById(dto.getMenuId())
                .orElse(null) : null;

        RestaurantImage image = RestaurantImage.builder()
                .imageUrl(dto.getImageUrl())
                .isThumbnail(dto.isThumbnail())
                .orderIndex(dto.getOrderIndex())
                .type(dto.getType())
                .restaurant(restaurant)
                .menu(menu)
                .build();

        return repository.save(image).getId();
    }

    @Override
    public void update(Long id, RestaurantImageDto.Request dto) {
        RestaurantImage image = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Image not found"));

        image.update(dto.getImageUrl(), dto.isThumbnail(), dto.getOrderIndex(), dto.getType());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RestaurantImageDto.Response findById(Long id) {
        return repository.findById(id)
                .map(i -> RestaurantImageDto.Response.builder()
                        .id(i.getId())
                        .imageUrl(i.getImageUrl())
                        .isThumbnail(i.isThumbnail())
                        .orderIndex(i.getOrderIndex())
                        .type(i.getType())
                        .restaurantId(i.getRestaurant() != null ? i.getRestaurant().getId() : null)
                        .menuId(i.getMenu() != null ? i.getMenu().getId() : null)
                        .createDate(i.getCreateDate())
                        .updateDate(i.getUpdateDate())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Image not found"));
    }

    @Override
    public List<RestaurantImageDto.Response> findAll() {
        return repository.findAll().stream()
                .map(i -> RestaurantImageDto.Response.builder()
                        .id(i.getId())
                        .imageUrl(i.getImageUrl())
                        .isThumbnail(i.isThumbnail())
                        .orderIndex(i.getOrderIndex())
                        .type(i.getType())
                        .restaurantId(i.getRestaurant() != null ? i.getRestaurant().getId() : null)
                        .menuId(i.getMenu() != null ? i.getMenu().getId() : null)
                        .createDate(i.getCreateDate())
                        .updateDate(i.getUpdateDate())
                        .build())
                .collect(Collectors.toList());
    }
}
