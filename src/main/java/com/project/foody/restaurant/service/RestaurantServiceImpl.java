package com.project.foody.restaurant.service;

import java.util.stream.Collectors;

import com.project.foody.restaurant.dto.FacilityDto;
import com.project.foody.restaurant.dto.RestaurantImageDto;
import com.project.foody.restaurant.dto.RestaurantMenuDto;
import com.project.foody.restaurant.entity.Facility;
import com.project.foody.restaurant.entity.RestaurantImage;
import com.project.foody.restaurant.entity.RestaurantMenu;
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
        // ✅ Facility 추가
        if (dto.getFacilities() != null) {
            for (FacilityDto.Request f : dto.getFacilities()) {
                Facility facility = Facility.builder()
                        .name(f.getName())
                        .restaurant(restaurant) // 연관관계 설정
                        .build();
                restaurant.addFacility(facility); // 양방향 유지
            }
        }

        // ✅ Image 추가
        if (dto.getImages() != null) {
            for (RestaurantImageDto.Request i : dto.getImages()) {
                RestaurantImage image = RestaurantImage.builder()
                        .imageUrl(i.getImageUrl())
                        .restaurant(restaurant)
                        .build();
                restaurant.addImage(image);
            }
        }

        // ✅ Menu 추가
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
    @Override
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

                // ✅ 연관된 항목들 매핑
                .facilities(restaurant.getFacilities().stream()
                        .map(f -> FacilityDto.Response.builder().name(f.getName()).build())
                        .collect(Collectors.toList()))

                .images(restaurant.getImages().stream()
                        .map(i -> RestaurantImageDto.Response.builder().imageUrl(i.getImageUrl()).build())
                        .collect(Collectors.toList()))

                .menus(restaurant.getMenus().stream()
                        .map(m -> RestaurantMenuDto.Response.builder()
                                .name(m.getName())
                                .price(m.getPrice())
                                .description(m.getDescription())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    // 음식점 삭제
    @Override
    public void delete(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));

        restaurantRepository.delete(restaurant);
    }

    // 음식점 전체 조회
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
