package com.rating.service.Services;

import com.rating.service.Models.Rating;

import java.util.List;

public interface RatingService {
    Rating addRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByHotelID(String hotelID);

    List<Rating> getRatingsByUserID(String userID);
}
