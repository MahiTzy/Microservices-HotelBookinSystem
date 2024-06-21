package com.rating.service.Controllers;

import com.rating.service.Models.Rating;
import com.rating.service.Services.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAuthority('SCOPE_internal')")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.addRating(rating));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

//    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/getByHotelID/{hotelID}")
    public ResponseEntity<?> getRatingsByHotelID(@PathVariable String hotelID) {
        return ResponseEntity.ok(ratingService.getRatingsByHotelID(hotelID));
    }

    @GetMapping("/getByUserID/{userID}")
//    @PreAuthorize("hasAuthority('SCOPE_internal')")
    public ResponseEntity<?> getRatingsByUserID(@PathVariable String userID) {
        return ResponseEntity.ok(ratingService.getRatingsByUserID(userID));
    }
}
