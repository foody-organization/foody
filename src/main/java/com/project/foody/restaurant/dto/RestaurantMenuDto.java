package com.project.foody.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.foody.base.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

public interface RestaurantMenuDto {

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    class Request {
        private String name;        // 메뉴 이름
        private int price;          // 메뉴 가격
        private String description; // 메뉴 설명 (선택)
    }

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Response extends BaseResponseDto {
        private String name;        // 메뉴 이름
        private int price;          // 메뉴 가격
        private String description; // 메뉴 설명
    }
}
