// com/project/foody/review/api/ReviewApiController.java
package com.project.foody.review.api;

import com.project.foody.review.entity.Review;
import com.project.foody.review.repo.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewApiController {

    private final ReviewRepository reviewRepository;

    // 목록 (placeId 로 조회)
    @GetMapping(produces = "application/json")
    public List<Review> list(@RequestParam String placeId) {
        return reviewRepository.findByPlaceIdOrderByCreateDateDesc(placeId);
    }

    // 생성
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Review create(@RequestBody Review payload) {
        if (payload.getPlaceId() == null || payload.getPlaceId().isBlank()
                || payload.getNickname() == null || payload.getNickname().isBlank()
                || payload.getRating() == null) {
            throw new IllegalArgumentException("필수 값 누락");
        }
        return reviewRepository.save(payload);
    }

    // 수정
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Review update(@PathVariable Long id, @RequestBody Review payload) {
        Review r = reviewRepository.findById(id).orElseThrow();
        if (payload.getRating() != null)   r.setRating(payload.getRating());
        if (payload.getContent() != null)  r.setContent(payload.getContent());
        if (payload.getNickname() != null) r.setNickname(payload.getNickname());
        return reviewRepository.save(r);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
