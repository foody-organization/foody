package com.project.foody.restaurant.entity;

import com.project.foody.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLRestriction("deleted = false")
@SQLDelete(sql = "update restaurantfacility set deleted = true where id = ?")
public class RestaurantFacility extends BaseEntity {

    @ManyToOne(fetch = LAZY, optional = false)          // ① optional=false
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = LAZY, optional = false)          // ② optional=false
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

}
