package com.project.foody.board.service;

import com.project.foody.base.service.CrudService;
import com.project.foody.board.dto.BoardDto;

public interface BoardService extends CrudService<BoardDto.Request, BoardDto.Response, Long> {
}
