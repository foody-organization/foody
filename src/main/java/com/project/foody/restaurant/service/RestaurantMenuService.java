package com.project.foody.restaurant.service;

import com.project.foody.base.service.CrudService;
import com.project.foody.restaurant.dto.RestaurantDto;
import com.project.foody.restaurant.dto.RestaurantMenuDto;
import com.project.foody.restaurant.entity.Restaurant;
import com.project.foody.restaurant.entity.RestaurantMenu;
import com.project.foody.restaurant.repository.RestaurantMenuRepository;
import com.project.foody.restaurant.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RestaurantMenuService extends CrudService<RestaurantMenuDto.Request, RestaurantMenuDto.Response, Long> {

    // 메뉴 생성 (음식점 ID와 함께)
//    Long create(RestaurantMenuDto.Request dto); // ← restaurantId는 dto에서 꺼내도록
    Long create(Long restaurantId, RestaurantMenuDto.Request dto);

    // 메뉴 수정
    void update(Long id, RestaurantMenuDto.Request dto);

    // 메뉴 단건 조회
    RestaurantMenuDto.Response findById(Long id);

    // 전체 메뉴 조회
    List<RestaurantMenuDto.Response> findAll();

    // 메뉴 삭제
    void delete(Long id);

}