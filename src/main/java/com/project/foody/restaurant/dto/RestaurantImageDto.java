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
 * 음식점 이미지에 대한 DTO
 */
public interface RestaurantImageDto {

    /**
     * 이미지 등록 요청 시 사용하는 DTO
     */
    @Getter
    @Setter
    @NoArgsConstructor // JSON → 객체 변환(Jackson 역직렬화)을 위해 기본 생성자 필요
    @AllArgsConstructor(access = AccessLevel.PROTECTED) // Builder를 통해 생성하는 것을 유도하고, 외부에서 직접 new 생성 제한
    @Builder
    class Request {
        private String imageUrl; // 이미지 파일의 URL 또는 경로
    }

    /**
     * 이미지 조회 응답 시 사용하는 DTO
     */
    @Getter
    @SuperBuilder
    @NoArgsConstructor // JSON → 객체 변환(Jackson 역직렬화)을 위해 기본 생성자 필요
    @AllArgsConstructor(access = AccessLevel.PROTECTED) // Builder를 통해 생성하는 것을 유도하고, 외부에서 직접 new 생성 제한
    class Response extends BaseResponseDto {
        private String imageUrl; // 이미지 파일 URL
    }
}
