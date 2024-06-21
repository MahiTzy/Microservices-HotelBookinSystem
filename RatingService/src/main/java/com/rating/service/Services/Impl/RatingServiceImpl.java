package com.rating.service.Services.Impl;

import com.rating.service.Models.Rating;
import com.rating.service.Repositories.RatingRepo;
import com.rating.service.Services.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;

    public RatingServiceImpl(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    @Override
    public Rating addRating(Rating rating) {
        String uuid = UUID.randomUUID().toString();
        rating.setRatingID(uuid);
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingsByHotelID(String hotelID) {
        return ratingRepo.findByHotelID(hotelID);
    }

    @Override
    public List<Rating> getRatingsByUserID(String userID) {
        return ratingRepo.findByUserID(userID);
    }
}
