package com.project.foody.comment.dto;

import com.project.foody.base.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

public interface CommentDto {

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    class Request {
        private String title;
        private String content;
    }

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @SuperBuilder
    class Response extends BaseResponseDto {
        private String title;
        private String content;
    }
}
