package com.project.foody.restaurant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.*;

/**
 * 음식점의 편의시설 정보를 저장하는 엔티티입니다.
 * 하나의 음식점은 여러 편의시설을 가질 수 있으며,
 * 각 편의시설은 반드시 하나의 음식점에 속합니다.
 */
@Entity
@Getter
@AllArgsConstructor // 👈 builder를 쓸 때 같이 필요
@Builder             // ✅ builder 메서드 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 프록시 생성을 위한 생성자
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "restaurant") // 무한 순환 방지를 위해 restaurant 제외
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String name; // 편의시설 이름 (예: "주차장", "와이파이")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false) // 음식점과 반드시 연관되어야 하며, 단독으로 존재할 수 없기 때문에 nullable = false 설정
    private Restaurant restaurant;

    // ✅ 연관관계 설정용 메서드 추가
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
