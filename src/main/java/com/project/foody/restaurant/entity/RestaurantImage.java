package com.project.foody.restaurant.entity;

import com.project.foody.base.entity.BaseEntity;
import com.project.foody.restaurant.enums.ImageType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@SQLDelete(sql = "update restaurantImage set deleted = true where id = ?")
public class RestaurantImage extends BaseEntity {

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private boolean isThumbnail;

    @Column(nullable = false)
    private int orderIndex;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ImageType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public void update(String imageUrl, boolean isThumbnail, int orderIndex, ImageType type) {
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
        this.orderIndex = orderIndex;
        this.type = type;
    }
}
