package com.project.foody.review.entity;

import com.project.foody.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "reviews",
        indexes = {
                @Index(name = "idx_review_place_id", columnList = "place_id")
        }
)
public class Review extends BaseEntity {

    @Column(name = "place_id", nullable = false, length = 50)  // ✅ 컬럼명 명확히
    private String placeId;

    @Column(nullable = false)
    private String placeName;

    private String address;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false)
    private Integer rating;

    @Lob
    private String content;

}
