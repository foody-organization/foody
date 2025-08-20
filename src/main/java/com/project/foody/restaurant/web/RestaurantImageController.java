package com.project.foody.restaurant.web;

import com.project.foody.base.controller.CrudController;
import com.project.foody.restaurant.dto.RestaurantImageDto;
import com.project.foody.restaurant.service.RestaurantImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant/restaurant-image")
@RequiredArgsConstructor
public class RestaurantImageController implements CrudController<RestaurantImageDto.Request, RestaurantImageDto.Response, Long> {

    private final RestaurantImageService restaurantImageService;

    // ✅ 이미지 등록
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody RestaurantImageDto.Request dto) {
        return ResponseEntity.ok(restaurantImageService.create(dto));
    }

    // ✅ 이미지 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantImageDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantImageService.findById(id));
    }

    // ✅ 이미지 전체 조회
    @GetMapping
    public ResponseEntity<List<RestaurantImageDto.Response>> findAll() {
        return ResponseEntity.ok(restaurantImageService.findAll());
    }

    // ✅ 이미지 수정
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody RestaurantImageDto.Request dto) {
        restaurantImageService.update(id, dto);
    }

    // ✅ 이미지 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        restaurantImageService.delete(id);
    }
}
