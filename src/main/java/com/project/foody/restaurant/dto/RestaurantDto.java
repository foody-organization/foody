package com.project.foody.restaurant.dto;

import com.project.foody.base.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

public interface RestaurantDto {

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    class Request {
        private String name;
        private String address;
    }

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @SuperBuilder
    class Response extends BaseResponseDto {
        private String name;
        private String address;
    }
}
