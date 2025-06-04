package com.project.foody.restaurant.entity;

import com.project.foody.base.entity.BaseEntity;
import jakarta.persistence.*;
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
public class Restaurant extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false, length = 1000)
    private String description;

    // 다대다 편의시설 연결
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RestaurantFacility> restaurantFacilities = new HashSet<>();

    public void addFacility(Facility facility) {
        RestaurantFacility rf = RestaurantFacility.builder()
                .restaurant(this)
                .facility(facility)
                .build();
        this.restaurantFacilities.add(rf);
        facility.getRestaurantFacilities().add(rf);
    }

    // 💡 카테고리, 영업시간, 태그 등은 추후 확장 가능

    public List<Facility> getFacilities() {
        return restaurantFacilities.stream()
                .map(RestaurantFacility::getFacility)
                .collect(Collectors.toList());
    }




//    @ManyToMany
//    @JoinTable(
//            name = "restaurant_facility",
//            joinColumns = @JoinColumn(name = "restaurant_id"),
//            inverseJoinColumns = @JoinColumn(name = "facility_id")
//    )
//    private List<Facility> facilities = new ArrayList<>();

    // ✅ 음식점 이미지 - 일대다 양방향
    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RestaurantImage> images = new HashSet<>();

    public void addImage(RestaurantImage image) {
        this.images.add(image);
        image.setRestaurant(this);
    }

    // ✅ 음식점 메뉴 - 일대다 양방향
    @Builder.Default
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RestaurantMenu> menus = new HashSet<>();

    public void addMenu(RestaurantMenu menu) {
        this.menus.add(menu);
        menu.setRestaurant(this);
    }

    // 💡 카테고리, 영업시간, 태그 등은 추후 확장 가능
}
