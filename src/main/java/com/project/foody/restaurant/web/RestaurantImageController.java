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
@RequestMapping("/restaurant-image")
@RequiredArgsConstructor
public class RestaurantImageController implements CrudController<RestaurantImageDto.Request, RestaurantImageDto.Response, Long> {

    private final RestaurantImageService imageService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody RestaurantImageDto.Request dto) {
        return ResponseEntity.ok(imageService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantImageDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.findById(id));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody RestaurantImageDto.Request dto) {
        imageService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        imageService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantImageDto.Response>> findAll() {
        return ResponseEntity.ok(imageService.findAll());
    }
}
