package com.project.foody.restaurant.dto;

import com.project.foody.base.dto.BaseResponseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public interface RestaurantDto {

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    class Request {
        private Long id;
        private String name;                       // 음식점 이름
        private String address;                    // 음식점 주소
        private String description;                // 음식점 설명

        private List<FacilityDto.Request> facilities;          // 편의시설 목록
        private List<RestaurantImageDto.Request> images;       // 이미지 목록
        private List<RestaurantMenuDto.Request> menus;         // 메뉴 목록
    }

    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @SuperBuilder
    class Response extends BaseResponseDto {
        private String name;                       // 음식점 이름
        private String address;                    // 음식점 주소
        private String description;                // 음식점 설명

        private List<FacilityDto.Response> facilities;
        private List<RestaurantImageDto.Response> images;
        private List<RestaurantMenuDto.Response> menus;
    }
}
