// com.project.foody.review.repo.ReviewRepository
package com.project.foody.review.repo;

import com.project.foody.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaceIdOrderByCreateDateDesc(String placeId); // ✅ createDate로
}
