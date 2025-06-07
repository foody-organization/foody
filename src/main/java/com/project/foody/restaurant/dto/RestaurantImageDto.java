package com.project.foody.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.foody.base.dto.BaseResponseDto;
import com.project.foody.restaurant.enums.ImageType;
import lombok.*;
import lombok.experimental.SuperBuilder;

public interface RestaurantImageDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    class Request {
        private String imageUrl;
        private boolean isThumbnail;
        private int orderIndex;
        private ImageType type;
        private Long restaurantId;   // 연관된 음식점 ID
        private Long menuId;         // 연관된 메뉴 ID
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Response extends BaseResponseDto {
        private Long id;
        private String imageUrl;
        private boolean isThumbnail;
        private int orderIndex;
        private ImageType type;
        private Long restaurantId;   // 음식점 ID
        private Long menuId;         // 메뉴 ID
    }
}
