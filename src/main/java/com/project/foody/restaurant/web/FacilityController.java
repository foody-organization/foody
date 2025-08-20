package com.project.foody.restaurant.web;

import com.project.foody.base.controller.CrudController;
import com.project.foody.restaurant.dto.FacilityDto;
import com.project.foody.restaurant.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant/facilities")
public class FacilityController implements CrudController<FacilityDto.Request, FacilityDto.Response, Long> {

    private final FacilityService facilityService;


    // 등록 (restaurantId가 필요한 경우 별도 엔드포/facilities인트 추가 가능)
    @Override
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody FacilityDto.Request dto) {
        return ResponseEntity.ok(facilityService.create(dto));
    }

    // 단건 조회
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FacilityDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(facilityService.findById(id));
    }

    // 전체 조회
    @Override
    @GetMapping
    public ResponseEntity<List<FacilityDto.Response>> findAll() {
        return ResponseEntity.ok(facilityService.findAll());
    }

    // 수정
    @Override
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody FacilityDto.Request dto) {
        facilityService.update(id, dto);
    }

    // 삭제
    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facilityService.delete(id);
    }
}
