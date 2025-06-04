package com.project.foody.restaurant.dto;

import com.project.foody.base.dto.BaseResponseDto;
import com.project.foody.restaurant.enums.ImageType;
import lombok.*;
import lombok.experimental.SuperBuilder;

public interface RestaurantImageDto {

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    class Request {
        private String imageUrl;
        private boolean isThumbnail;
        private int orderIndex;
        private ImageType type;
        private Long restaurantId;
        private Long menuId;
    }

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @SuperBuilder
    class Response extends BaseResponseDto {
        private String imageUrl;
        private boolean isThumbnail;
        private int orderIndex;
        private ImageType type;
        private Long restaurantId;
        private Long menuId;
    }
}
