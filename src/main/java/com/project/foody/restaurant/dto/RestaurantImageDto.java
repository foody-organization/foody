package com.project.foody.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.foody.base.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

public interface RestaurantImageDto {

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    class Request {
        private String imageUrl; // 이미지 파일의 URL 또는 경로
    }

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL) // null 값은 응답에 포함하지 않음
    class Response extends BaseResponseDto {
        private String imageUrl; // 이미지 파일 URL
    }
}
