package com.project.foody.restaurant.dto;

import com.project.foody.base.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

public interface MenuDto {

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    class Request {
        private String name;
        private int price;
        private Long restaurantId;
    }

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @SuperBuilder
    class Response extends BaseResponseDto {
        private String name;
        private int price;
        private Long restaurantId;
    }
}
