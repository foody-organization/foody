// com.project.foody.review.dto.ReviewDto
package com.project.foody.review.dto;

import com.project.foody.base.dto.BaseResponseDto;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

public interface ReviewDto {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    class Request {
        @NotBlank private String placeId;
        @NotBlank private String placeName;
        private String address;
        @NotBlank private String nickname;
        @NotNull @Min(1) @Max(5) private Integer rating;
        @NotBlank private String content;
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    @SuperBuilder
    class Response extends BaseResponseDto {
        private Long   id;
        private String placeId;
        private String placeName;
        private String address;
        private String nickname;
        private Integer rating;
        private String content;
        // BaseResponseDto에 createDate, updateDate 포함 가정
    }
}
