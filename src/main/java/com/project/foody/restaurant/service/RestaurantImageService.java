package com.project.foody.restaurant.service;

import com.project.foody.base.service.CrudService;
import com.project.foody.restaurant.dto.RestaurantImageDto;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.entity.RestaurantImage;
import com.project.foody.restaurant.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RestaurantImageService extends CrudService<RestaurantImageDto.Request, RestaurantImageDto.Response, Long> {

    Long create(RestaurantImageDto.Request dto);
    RestaurantImageDto.Response findById(Long id);
    List<RestaurantImageDto.Response> findAll();
    void update(Long id, RestaurantImageDto.Request dto);
    void delete(Long id);

}
