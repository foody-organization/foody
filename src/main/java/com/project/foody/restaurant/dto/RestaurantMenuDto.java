package com.project.foody.restaurant.dto;

import com.project.foody.base.dto.BaseResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 음식점 메뉴에 대한 요청 및 응답 DTO
 */
public interface RestaurantMenuDto {

    /**
     * 메뉴 등록 또는 수정 요청 시 사용하는 DTO
     */
    @Getter
    @Setter
    @NoArgsConstructor // JSON → 객체 변환(Jackson 역직렬화)을 위해 기본 생성자 필요
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    class Request {
        private String name;        // 메뉴 이름
        private int price;          // 메뉴 가격
        private String description; // 메뉴 설명 (선택)
    }

    /**
     * 메뉴 조회 응답 시 사용하는 DTO
     */
    @Getter
    @SuperBuilder
    @NoArgsConstructor // JSON → 객체 변환(Jackson 역직렬화)을 위해 기본 생성자 필요
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    class Response extends BaseResponseDto {
        private String name;        // 메뉴 이름
        private int price;          // 메뉴 가격
        private String description; // 메뉴 설명
    }
}
