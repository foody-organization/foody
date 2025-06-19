package com.project.foody.restaurant.service;

import com.project.foody.base.service.CrudService;
import com.project.foody.restaurant.dto.RestaurantDto;

import java.util.List;


public interface RestaurantService extends CrudService<RestaurantDto.Request, RestaurantDto.Response, Long> {
    Long create(RestaurantDto.Request dto);
    void update(Long id, RestaurantDto.Request dto);
    RestaurantDto.Response findById(Long id);
    void delete(Long id);
    List<RestaurantDto.Response> findAll();

    // ✅ 추가
    void addFacilityToRestaurant(Long restaurantId, Long facilityId);
}
