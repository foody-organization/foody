package com.project.foody.board.dto;

import com.project.foody.base.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

public interface BoardDto {

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    class Request {
        private String title;
        private String content;
        //회원 회원테이블 컬럼명 바뀌면 수정예정
        private String mem;
    }

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @SuperBuilder
    class Response extends BaseResponseDto {
        private String title;
        private String content;
        //회원 회원테이블 컬럼명 바뀌면 수정예정
        private String mem;
    }
}
