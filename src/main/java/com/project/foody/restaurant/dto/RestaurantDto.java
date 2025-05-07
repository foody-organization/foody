package com.project.foody.restaurant.dto;

import com.project.foody.base.dto.BaseResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.AccessLevel;

public interface RestaurantDto {
    

    // 음식점 등록 및 수정 요청 시 사용하는 DTO
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder   
    class Request {
        private String name; // 음식점 이름
        private String address; // 음식점 주소
        private String description; // 음식점 설명명

    } 

    // 음식점 조회 시 반환되는 DTO
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @SuperBuilder
    class Response extends BaseResponseDto {
        private String name;
        private String address;
        private String description;
    }

        
    

}
