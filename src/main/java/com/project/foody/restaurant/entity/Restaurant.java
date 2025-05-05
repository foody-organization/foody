package com.project.foody.restaurant.entity;

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

    @Column(length = 200)
    private String address; // 음식점 주소

    @Column(length = 1000)
    private String description; // 음식점 소개개

}
