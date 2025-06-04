package com.project.foody.restaurant.web;

import com.project.foody.base.controller.CrudController;
import com.project.foody.restaurant.dto.FacilityDto;
import com.project.foody.restaurant.service.FacilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/facility")
@RequiredArgsConstructor
public class FacilityController implements CrudController<FacilityDto.Request, FacilityDto.Response, Long> {

    private final FacilityService facilityService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody FacilityDto.Request dto) {
        return ResponseEntity.ok(facilityService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(facilityService.findById(id));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody FacilityDto.Request dto) {
        facilityService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facilityService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<FacilityDto.Response>> findAll() {
        return ResponseEntity.ok(facilityService.findAll());
    }
}
