package com.project.foody.board.service;

import com.project.foody.board.dto.BoardDto;
import com.project.foody.board.entity.Board;
import com.project.foody.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long create(BoardDto.Request boardDto) {


        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .build();

        return boardRepository.save(board).getId();
    }

    @Override
    public void update(Long id, BoardDto.Request boardDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found id: " + id));

        board.update(boardDto.getTitle(), boardDto.getContent());
    }

    @Override
    public BoardDto.Response findById(Long id) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Board not found id: " + id));

        return BoardDto.Response.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .updateDate(board.getUpdateDate())
                .build();
    }

    @Override
    public void delete(Long id) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Board not found id: " + id));

        boardRepository.delete(board);
    }

    @Override
    public List<BoardDto.Response> findAll() {
        return boardRepository.findAll().stream()
                .map(board -> BoardDto.Response.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .createDate(board.getCreateDate())
                        .updateDate(board.getUpdateDate())
                        .build())
                .collect(Collectors.toList());
    }

}
