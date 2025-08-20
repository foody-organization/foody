package com.project.foody.review.service;

import com.project.foody.review.dto.ReviewDto;
import com.project.foody.review.entity.Review;
import com.project.foody.review.repo.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDto.Response create(ReviewDto.Request req) {
        Review review = Review.builder()
                .placeId(req.getPlaceId())
                .placeName(req.getPlaceName())
                .address(req.getAddress())
                .nickname(req.getNickname())
                .rating(req.getRating())
                .content(req.getContent())
                .build();

        Review saved = reviewRepository.save(review);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto.Response> findByPlaceId(String placeId) {
        return reviewRepository.findByPlaceIdOrderByCreateDateDesc(placeId)
                .stream()
                .map(this::toResponse)              // 헬퍼로 Response 생성
                .collect(Collectors.toList());
    }

    // ===== private mapper =====
    private ReviewDto.Response toResponse(Review r) {
        return ReviewDto.Response.builder()
                .id(r.getId())
                .placeId(r.getPlaceId())
                .placeName(r.getPlaceName())
                .address(r.getAddress())
                .nickname(r.getNickname())
                .rating(r.getRating())
                .content(r.getContent())
                .createDate(r.getCreateDate())
                .updateDate(r.getUpdateDate())
                .build();
    }
}
