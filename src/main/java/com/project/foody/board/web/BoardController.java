package com.project.foody.board.web;

import com.project.foody.base.controller.CrudController;
import com.project.foody.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.foody.board.dto.BoardDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController implements CrudController<BoardDto.Request, BoardDto.Response, Long> {

    private final BoardService boardService;

    @Override
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody BoardDto.Request boardDto) {
        return ResponseEntity.ok(boardService.create(boardDto));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BoardDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.findById(id));
    }

    @Override
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody BoardDto.Request boardDto) {
        boardService.update(id, boardDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BoardDto.Response>> findAll() {
        return ResponseEntity.ok(boardService.findAll());
    }

}
