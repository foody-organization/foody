package com.project.foody.restaurant.web;

import com.project.foody.restaurant.dto.FacilityDto;
import com.project.foody.restaurant.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Long> addFacility(
            @PathVariable Long restaurantId,
            @RequestBody FacilityDto.Request request
    ) {
        Long id = facilityService.addFacility(restaurantId, request);
        return ResponseEntity.ok(id);
    }
}

