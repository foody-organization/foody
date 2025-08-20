// com.project.foody.review.service.ReviewService
package com.project.foody.review.service;

import com.project.foody.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto.Response create(ReviewDto.Request req);
    List<ReviewDto.Response> findByPlaceId(String placeId);
}
