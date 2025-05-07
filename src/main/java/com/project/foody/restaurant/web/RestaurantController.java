package com.project.foody.restaurant.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.foody.base.controller.CrudController;
import com.project.foody.restaurant.dto.RestaurantDto;
import com.project.foody.restaurant.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController implements CrudController<RestaurantDto.Request, RestaurantDto.Response, Long> {

    private final RestaurantService restaurantService;

    // 음식점 등록
    @Override
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody RestaurantDto.Request dto) {
        return ResponseEntity.ok(restaurantService.create(dto));
    }

    // 음식점 단건 조회
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    // 음식점 수정
    @Override
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody RestaurantDto.Request dto) {
        restaurantService.update(id, dto);
    }

    // 음식점 삭제
    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        restaurantService.delete(id);
    }

    // 음식점 전체 조회
    @Override
    @GetMapping
    public ResponseEntity<List<RestaurantDto.Response>> findAll() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

}
