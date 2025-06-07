package com.project.foody.restaurant.service;

import com.project.foody.restaurant.dto.RestaurantMenuDto;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.entity.RestaurantMenu;
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
public class RestaurantMenuServiceImpl implements RestaurantMenuService {

    private final RestaurantMenuRepository restaurantMenuRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Long create(RestaurantMenuDto.Request dto) {
        return create(dto.getRestaurantId(), dto); // 기존 메서드 재사용
    }

    @Override
    public Long create(Long restaurantId, RestaurantMenuDto.Request dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("음식점을 찾을 수 없습니다."));

        RestaurantMenu menu = RestaurantMenu.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .restaurant(restaurant)
                .build();

        return restaurantMenuRepository.save(menu).getId();
    }


    @Override
    public void update(Long id, RestaurantMenuDto.Request dto) {
        RestaurantMenu menu = restaurantMenuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("메뉴를 찾을 수 없습니다."));

        menu.update(dto.getName(), dto.getPrice(), dto.getDescription());
    }

    @Override
    public RestaurantMenuDto.Response findById(Long id) {
        RestaurantMenu menu = restaurantMenuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("메뉴를 찾을 수 없습니다."));

        return RestaurantMenuDto.Response.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .createDate(menu.getCreateDate())
                .updateDate(menu.getUpdateDate())
                .restaurantId(menu.getRestaurant().getId())
                .build();
    }

    @Override
    public void delete(Long id) {
        RestaurantMenu menu = restaurantMenuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("메뉴를 찾을 수 없습니다."));

        restaurantMenuRepository.delete(menu);
    }

    @Override
    public List<RestaurantMenuDto.Response> findAll() {
        return restaurantMenuRepository.findAll().stream()
                .map(menu -> RestaurantMenuDto.Response.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .description(menu.getDescription())
                        .createDate(menu.getCreateDate())
                        .updateDate(menu.getUpdateDate())
                        .restaurantId(menu.getRestaurant().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
