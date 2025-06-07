package com.project.foody.restaurant.entity;

import com.project.foody.base.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Facility extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private boolean deleted = false;


    // 중간 엔티티(RestaurantFacility)를 통한 연관관계
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantFacility> restaurantFacilities = new ArrayList<>();

    // 편의시설 이름 수정
    public void update(String name) {
        this.name = name;
    }

    public void addRestaurantFacility(RestaurantFacility rf) {
        this.restaurantFacilities.add(rf);
        rf.setFacility(this);
    }
    //    public List<Restaurant> getRestaurants() {
//        return restaurantFacilities.stream()
//                .map(RestaurantFacility::getRestaurant)
//                .collect(Collectors.toList());
//    }

}

