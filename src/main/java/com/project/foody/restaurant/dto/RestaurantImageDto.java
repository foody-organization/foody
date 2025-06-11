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
        private Long restaurantId;
        private Long menuId;
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

        // 여기에 없으면 서비스단에서 없는 메서드로 오류가 생김
        private Long restaurantId;
        private Long menuId;

    }
}
