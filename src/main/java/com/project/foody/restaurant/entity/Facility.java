package com.project.foody.restaurant.entity;

import com.project.foody.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update facility set deleted = true where id = ?")
@ToString(exclude = "restaurants")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

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
