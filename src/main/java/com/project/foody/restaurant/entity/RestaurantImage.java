package com.project.foody.restaurant.entity;

import com.project.foody.base.entity.BaseEntity;
import com.project.foody.restaurant.enums.ImageType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update restaurant_image set deleted = true where id = ?")
@ToString(exclude = "restaurant")
public class RestaurantImage extends BaseEntity {

    @Column(nullable = false)
    private String imageUrl;

    private boolean isThumbnail;

    @Column(nullable = false)
    private int orderIndex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImageType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private RestaurantMenu menu;

    public void update(String imageUrl, boolean isThumbnail, int orderIndex, ImageType type) {
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
        this.orderIndex = orderIndex;
        this.type = type;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setMenu(RestaurantMenu menu) {
        this.menu = menu;
    }

}
