package com.project.foody.restaurant.web;

import com.project.foody.restaurant.dto.RestaurantMenuDto;
import com.project.foody.restaurant.service.RestaurantMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant-menus")
public class RestaurantMenuController {

    private final RestaurantMenuService restaurantMenuService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Long> addMenu(
            @PathVariable Long restaurantId,
            @RequestBody RestaurantMenuDto.Request request
    ) {
        Long id = restaurantMenuService.addMenu(restaurantId, request);
        return ResponseEntity.ok(id);
    }
}
