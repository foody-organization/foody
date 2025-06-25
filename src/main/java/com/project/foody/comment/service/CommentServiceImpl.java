package com.project.foody.comment.service;

import com.project.foody.comment.entity.Comment;
import com.project.foody.comment.dto.CommentDto;
import com.project.foody.comment.repository.CommentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Long create(CommentDto.Request commentDto) {


    	Comment comment = Comment.builder()
                .title(commentDto.getTitle())
                .content(commentDto.getContent())
                .build();

        return commentRepository.save(comment).getId();
    }

    @Override
    public void update(Long id, CommentDto.Request commentDto) {

    	Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found id: " + id));

    	Comment updatedComment = Comment.builder()
                .id(comment.getId())
                .title(commentDto.getTitle())
                .content(commentDto.getContent())
                .build();

        commentRepository.save(updatedComment);
    }

    @Override
    public CommentDto.Response findById(Long id) {

    	Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found id: " + id));

        return CommentDto.Response.builder()
                .id(comment.getId())
                .title(comment.getTitle())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
                .updateDate(comment.getUpdateDate())
                .build();
    }

    @Override
    public void delete(Long id) {

    	Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found id: " + id));

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto.Response> findAll() {
        return commentRepository.findAll().stream()
                .map(comment -> CommentDto.Response.builder()
                        .id(comment.getId())
                        .title(comment.getTitle())
                        .content(comment.getContent())
                        .createDate(comment.getCreateDate())
                        .updateDate(comment.getUpdateDate())
                        .build())
                .collect(Collectors.toList());
    }

}
