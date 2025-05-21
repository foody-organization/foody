package com.project.foody.restaurant.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.project.foody.base.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter // 모든 필드에 대해 getter 자동생성
@SuperBuilder // 상속받은 클래스의 필드까지 builder 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성 + 외부에서
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 생성
@DynamicInsert // null 필드 제외 insert
@DynamicUpdate // 변경된 필드만 update

public class Restaurant extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name; // 음식점 이름

    @Column(nullable = false, length = 200)
    private String address; // 음식점 주소

    @Column(nullable = false, length = 1000)
    private String description; // 음식점 소개


    // 다대일 null값이 허용되는것고 아닌것을 확인
    // 편의시설 목록 - Facility와의 일대다 관계 (양방향 연관관계)
    // 원하는 편의시설을 검색해서 식당을 찾을 수 있게 하기 위해 양방향을 구현함
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Facility> facilities = new ArrayList<>();


    // 음식점 분류

    // 영업시간

    // (리뷰, 별점리뷰)

    // 태그

    // 위치정보(네이버에서 받을 수 있나?

    // 음식점 사진들
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantImage> images = new ArrayList<>();


    // 음식점 메뉴들
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantMenu> menus = new ArrayList<>();




}
