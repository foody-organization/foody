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
 * 음식점에 등록된 편의시설(예: 와이파이, 주차장 등)에 대한 DTO
 */
public interface FacilityDto {

    /**
     * 편의시설 등록/수정 요청 시 사용하는 DTO
     */
    @Getter
    @Setter
    @NoArgsConstructor // JSON → 객체 변환(Jackson 역직렬화)을 위해 기본 생성자 필요
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    class Request {
        private String name; // 편의시설 이름
    }

    /**
     * 편의시설 조회 응답 시 사용하는 DTO
     */
    @Getter
    @SuperBuilder
    @NoArgsConstructor // JSON → 객체 변환(Jackson 역직렬화)을 위해 기본 생성자 필요
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    class Response extends BaseResponseDto {
        private String name; // 편의시설 이름
    }
}
