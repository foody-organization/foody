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
 * 음식점의 개별 메뉴 정보를 담는 엔티티입니다.
 * 메뉴 이름, 가격, 설명을 포함하며,
 * 하나의 음식점(Restaurant)과 N:1 관계를 가집니다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 프록시 생성을 위한 기본 생성자
@AllArgsConstructor // 모든 필드 값을 인자로 받는 생성자
@Builder // 객체 생성 시 유연한 빌더 패턴 지원
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // ID 기준으로 equals/hashCode 정의
@ToString(exclude = "restaurant") // restaurant 필드는 toString에서 제외 (무한루프 방지)
public class RestaurantMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // equals/hashCode 기준 필드
    private Long id;

    @Column(nullable = false)
    private String name; // 메뉴 이름

    @Column(nullable = false)
    private int price; // 메뉴 가격

    @Column(length = 1000) // nullable=true는 기본값이므로 생략
    private String description; // 메뉴 설명 (선택 사항)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false) // 외래키는 필수
    private Restaurant restaurant;

    /**
     * 연관된 음식점 설정 (양방향 연관관계 유지를 위한 메서드)
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * 메뉴 정보 변경 메서드
     */
    public void changeMenu(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
