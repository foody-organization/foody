package com.project.foody.comment.web;

import com.project.foody.base.controller.CrudController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.foody.comment.dto.CommentDto;
import com.project.foody.comment.service.CommentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController implements CrudController<CommentDto.Request, CommentDto.Response, Long> {

    private final CommentService commentService;

    @Override
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CommentDto.Request commentDto) {
        return ResponseEntity.ok(commentService.create(commentDto));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto.Response> findById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findById(id));
    }

    @Override
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CommentDto.Request commentDto) {
    	commentService.update(id, commentDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	commentService.delete(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CommentDto.Response>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

}
