package com.rating.service.Repositories;

import com.rating.service.Models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends MongoRepository<Rating, String> {

    List<Rating> findByHotelID(String hotelID);

    List<Rating> findByUserID(String userID);
}
