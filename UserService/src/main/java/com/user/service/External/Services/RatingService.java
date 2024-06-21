package com.user.service.External.Services;

import com.user.service.Models.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @GetMapping("/ratings/getByUserID/{userId}")
    Rating getRatingByUserId(@PathVariable String userId);

    @PostMapping("/ratings/add")
    Rating createRating(Rating rating);

    @PutMapping("/ratings/update/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("/ratings/delete/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}
