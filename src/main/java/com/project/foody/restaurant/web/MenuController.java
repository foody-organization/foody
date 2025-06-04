package com.project.foody.restaurant.web;

import com.project.foody.base.controller.CrudController;
import com.project.foody.restaurant.dto.MenuDto;
import com.project.foody.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController implements CrudController<MenuDto.Request, MenuDto.Response, Long> {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody MenuDto.Request dto) {
        return ResponseEntity.ok(menuService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.findById(id));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody MenuDto.Request dto) {
        menuService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        menuService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<MenuDto.Response>> findAll() {
        return ResponseEntity.ok(menuService.findAll());
    }
}
