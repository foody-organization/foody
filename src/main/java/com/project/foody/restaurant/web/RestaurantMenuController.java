package com.project.foody.restaurant.web;

import com.project.foody.base.controller.CrudController;
import com.project.foody.restaurant.dto.RestaurantMenuDto;
import com.project.foody.restaurant.service.RestaurantMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class RestaurantMenuController implements CrudController<RestaurantMenuDto.Request, RestaurantMenuDto.Response, Long> {

    private final RestaurantMenuService restaurantMenuService;
    @Override
    public ResponseEntity<Long> create(@RequestBody RestaurantMenuDto.Request dto) {
        // dto에서 restaurantId 꺼내서 넘기기
        return ResponseEntity.ok(restaurantMenuService.create(dto.getRestaurantId(), dto));
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Long> create(@PathVariable Long restaurantId, @RequestBody RestaurantMenuDto.Request dto) {
        return ResponseEntity.ok(restaurantMenuService.create(restaurantId, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantMenuDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantMenuService.findById(id));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody RestaurantMenuDto.Request dto) {
        restaurantMenuService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        restaurantMenuService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantMenuDto.Response>> findAll() {
        return ResponseEntity.ok(restaurantMenuService.findAll());
    }
}
