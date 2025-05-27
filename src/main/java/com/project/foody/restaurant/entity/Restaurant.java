package com.project.foody.restaurant.entity;

import com.project.foody.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * 음식점 정보를 나타내는 엔티티입니다.
 * 편의시설, 이미지, 메뉴와 연관관계를 맺습니다.
 */
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 프록시용 기본 생성자
@AllArgsConstructor // 전체 필드 생성자
@DynamicInsert // null 제외한 값만 insert
@DynamicUpdate // 변경된 필드만 update
public class Restaurant extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name; // 음식점 이름

    @Column(nullable = false, length = 200)
    private String address; // 음식점 주소

    @Column(nullable = false, length = 1000)
    private String description; // 음식점 설명

    // Facility - 편의시설 리스트 (양방향)
    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Facility> facilities = new ArrayList<>();

    public void addFacility(Facility facility) {
        this.facilities.add(facility);
        facility.setRestaurant(this); // 양방향 연관관계 설정
    }

    // RestaurantImage - 음식점 이미지 리스트 (양방향)
    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantImage> images = new ArrayList<>();

    public void addImage(RestaurantImage image) {
        this.images.add(image);
        image.setRestaurant(this);
    }

    // RestaurantMenu - 음식점 메뉴 리스트 (양방향)
    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantMenu> menus = new ArrayList<>();

    public void addMenu(RestaurantMenu menu) {
        this.menus.add(menu);
        menu.setRestaurant(this);
    }

    // ✅ 나중에 카테고리, 영업시간, 태그 등 추가 가능
}
