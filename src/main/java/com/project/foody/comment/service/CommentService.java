package com.project.foody.comment.service;

import com.project.foody.base.service.CrudService;
import com.project.foody.comment.dto.CommentDto;

public interface CommentService extends CrudService<CommentDto.Request, CommentDto.Response, Long> {
}
