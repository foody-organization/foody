package com.project.foody.restaurant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AccessLevel;

/**
 * 음식점에 등록된 이미지 정보를 나타내는 엔티티입니다.
 * 여러 개의 이미지가 하나의 음식점에 연결될 수 있습니다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 프록시 생성을 위해 반드시 기본 생성자 필요 + 외부에서 직접 생성 제한
@AllArgsConstructor // 테스트나 내부에서 객체 초기화할 때 사용할 전체 필드 생성자
@Builder // 안정성과 가독성 등 때문에 사용
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // id 기준 equals/hashCode
@ToString(exclude = "restaurant") // restaurant 필드는 toString 제외 (무한 루프 방지)
public class RestaurantImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String imageUrl; // 이미지 파일 경로나 URL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false) // 음식점과 반드시 연관돼야 하며, 단독으로 존재할 수 없으므로 nullable = false 설정
    private Restaurant restaurant;


}
