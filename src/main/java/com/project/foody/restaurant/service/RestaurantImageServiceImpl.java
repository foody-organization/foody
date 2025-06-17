package com.project.foody.restaurant.service;

import com.project.foody.restaurant.dto.RestaurantImageDto;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.entity.RestaurantImage;
import com.project.foody.restaurant.entity.RestaurantMenu;
import com.project.foody.restaurant.enums.ImageType;
import com.project.foody.restaurant.repository.RestaurantImageRepository;
import com.project.foody.restaurant.repository.RestaurantMenuRepository;
import com.project.foody.restaurant.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RestaurantImageServiceImpl implements RestaurantImageService {

    private final RestaurantImageRepository restaurantImageRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;

    @Override
    public Long create(RestaurantImageDto.Request dto) {
        Restaurant restaurant = null;
        RestaurantMenu menu = null;

        if (dto.getRestaurantId() != null) {
            restaurant = restaurantRepository.findById(dto.getRestaurantId())
                    .orElseThrow(() -> new EntityNotFoundException("음식점을 찾을 수 없습니다."));
        }

        if (dto.getMenuId() != null) {
            menu = restaurantMenuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new EntityNotFoundException("메뉴를 찾을 수 없습니다."));
        }

        RestaurantImage image = RestaurantImage.builder()
                .imageUrl(dto.getImageUrl())
                .isThumbnail(dto.isThumbnail())
                .orderIndex(dto.getOrderIndex())
                .type(dto.getType())
                .restaurant(restaurant)
                .menu(menu)
                .build();

        return restaurantImageRepository.save(image).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantImageDto.Response findById(Long id) {
        RestaurantImage image = restaurantImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이미지를 찾을 수 없습니다."));

        return RestaurantImageDto.Response.builder()
                .id(image.getId())
                .imageUrl(image.getImageUrl())
                .isThumbnail(image.isThumbnail())
                .orderIndex(image.getOrderIndex())
                .type(image.getType())
                .restaurantId(image.getRestaurant() != null ? image.getRestaurant().getId() : null)
                .menuId(image.getMenu() != null ? image.getMenu().getId() : null)
                .createDate(image.getCreateDate())
                .updateDate(image.getUpdateDate())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantImageDto.Response> findAll() {
        return restaurantImageRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, RestaurantImageDto.Request dto) {
        RestaurantImage image = restaurantImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이미지를 찾을 수 없습니다."));

        image.update(dto.getImageUrl(), dto.isThumbnail(), dto.getOrderIndex(), dto.getType());
    }

    @Override
    public void delete(Long id) {
        RestaurantImage image = restaurantImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이미지를 찾을 수 없습니다."));
        restaurantImageRepository.delete(image);
    }

    private RestaurantImageDto.Response toDto(RestaurantImage image) {
        return RestaurantImageDto.Response.builder()
                .id(image.getId())
                .imageUrl(image.getImageUrl())
                .isThumbnail(image.isThumbnail())
                .orderIndex(image.getOrderIndex())
                .type(image.getType())
                .restaurantId(image.getRestaurant() != null ? image.getRestaurant().getId() : null)
                .menuId(image.getMenu() != null ? image.getMenu().getId() : null)
                .createDate(image.getCreateDate())
                .updateDate(image.getUpdateDate())
                .build();
    }
}
