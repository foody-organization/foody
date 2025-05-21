package com.project.foody.restaurant.web;

import com.project.foody.restaurant.dto.RestaurantImageDto;
import com.project.foody.restaurant.service.RestaurantImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant-images")
public class RestaurantImageController {

    private final RestaurantImageService restaurantImageService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Long> upload(
            @PathVariable Long restaurantId,
            @RequestBody RestaurantImageDto.Request request
    ) {
        Long id = restaurantImageService.uploadImage(restaurantId, request);
        return ResponseEntity.ok(id);
    }
}
