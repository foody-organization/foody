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

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody RestaurantDto.Request dto) {
        return ResponseEntity.ok(restaurantService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody RestaurantDto.Request dto) {
        restaurantService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        restaurantService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto.Response>> findAll() {
        return ResponseEntity.ok(restaurantService.findAll());
    }
}
