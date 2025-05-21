package com.project.foody.restaurant.dto;

import com.project.foody.base.dto.BaseResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 음식점 등록 및 조회에 사용되는 DTO 정의
 */
public interface RestaurantDto {

    /**
     * 음식점 등록 또는 수정 요청 시 사용하는 DTO
     */
    @Getter
    @Setter // JSON → 객체 변환을 위한 Setter
    @NoArgsConstructor // JSON → 객체 변환(Jackson 역직렬화)을 위해 기본 생성자 필요
    @AllArgsConstructor(access = AccessLevel.PROTECTED) // 이거 왜 ㅅㅏ용하는지 궁금
    @Builder
    class Request {
        private String name;         // 음식점 이름
        private String address;      // 음식점 주소
        private String description;  // 음식점 소개 문구

        // 연관된 하위 정보들
        private List<FacilityDto.Request> facilities;          // 편의시설 목록
        private List<RestaurantImageDto.Request> images;       // 이미지 목록
        private List<RestaurantMenuDto.Request> menus;         // 메뉴 목록
    }

    /**
     * 음식점 조회 응답 시 사용하는 DTO
     */
    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    class Response extends BaseResponseDto {
        private String name;         // 음식점 이름
        private String address;      // 음식점 주소
        private String description;  // 음식점 설명

        // 하위 엔티티 응답 리스트
        private List<FacilityDto.Response> facilities;
        private List<RestaurantImageDto.Response> images;
        private List<RestaurantMenuDto.Response> menus;
    }
}
