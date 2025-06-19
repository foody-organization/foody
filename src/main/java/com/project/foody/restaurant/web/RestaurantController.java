package com.project.foody.restaurant.web;

import com.project.foody.base.controller.CrudController;
import com.project.foody.restaurant.dto.RestaurantDto;
import com.project.foody.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController implements CrudController<RestaurantDto.Request, RestaurantDto.Response, Long> {

    private final RestaurantService restaurantService;

    // 음식점 등록
    @PostMapping
    @Override
    public ResponseEntity<Long> create(@RequestBody RestaurantDto.Request dto) {
        Long id = restaurantService.create(dto);
        return ResponseEntity.ok(id);
    }

    // 음식점 단건 조회
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<RestaurantDto.Response> findById(@PathVariable("id") Long id) {
        RestaurantDto.Response response = restaurantService.findById(id);
        return ResponseEntity.ok(response);
    }

    // 음식점 수정
    @PutMapping("/{id}")
    @Override
    public void update(@PathVariable("id") Long id, @RequestBody RestaurantDto.Request dto) {
        restaurantService.update(id, dto);
    }

    // 음식점 삭제
    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable("id") Long id) {
        restaurantService.delete(id);
    }

    // 음식점 전체 조회
    @GetMapping
    @Override
    public ResponseEntity<List<RestaurantDto.Response>> findAll() {
        List<RestaurantDto.Response> list = restaurantService.findAll();
        return ResponseEntity.ok(list);
    }

    // 음식점 편의시설추가
    @PostMapping("/{restaurantId}/facility/{facilityId}")
    public ResponseEntity<Void> addFacility(@PathVariable Long restaurantId, @PathVariable Long facilityId) {
        restaurantService.addFacilityToRestaurant(restaurantId, facilityId);
        return ResponseEntity.ok().build();
    }
}
