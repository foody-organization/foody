package com.project.foody.restaurant.service.impl;

import com.project.foody.restaurant.dto.MenuDto;
import com.project.foody.restaurant.entity.Menu;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.repository.MenuRepository;
import com.project.foody.restaurant.repository.RestaurantRepository;
import com.project.foody.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Long create(MenuDto.Request dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        Menu menu = Menu.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .restaurant(restaurant)
                .build();

        return menuRepository.save(menu).getId();
    }

    @Override
    public void update(Long id, MenuDto.Request dto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found"));

        menu.update(dto.getName(), dto.getPrice());
    }

    @Override
    public void delete(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public MenuDto.Response findById(Long id) {
        return menuRepository.findById(id)
                .map(m -> MenuDto.Response.builder()
                        .id(m.getId())
                        .name(m.getName())
                        .price(m.getPrice())
                        .restaurantId(m.getRestaurant().getId())
                        .createDate(m.getCreateDate())
                        .updateDate(m.getUpdateDate())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Menu not found"));
    }

    @Override
    public List<MenuDto.Response> findAll() {
        return menuRepository.findAll().stream()
                .map(m -> MenuDto.Response.builder()
                        .id(m.getId())
                        .name(m.getName())
                        .price(m.getPrice())
                        .restaurantId(m.getRestaurant().getId())
                        .createDate(m.getCreateDate())
                        .updateDate(m.getUpdateDate())
                        .build())
                .collect(Collectors.toList());
    }
}
