package com.user.service.Services.Impl;

import com.user.service.Exceptions.ResourceNotFoundException;
import com.user.service.External.Services.HotelService;
import com.user.service.Models.Hotel;
import com.user.service.Models.Rating;
import com.user.service.Models.User;
import com.user.service.Repositories.UserRepo;
import com.user.service.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    UserServiceImpl(UserRepo userRepo, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepo = userRepo;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
    }

    @Override
    public User saveUser(User user) {
        String uuid = UUID.randomUUID().toString();
        user.setUserID(uuid);
        return this.userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public User getUserById(String userId) {
        // Fetch the user by ID, throw an exception if not found
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Fetch ratings for the user
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/getByUserID/" + userId, Rating[].class);
        if (ratingOfUser != null) {
            for (Rating rating : ratingOfUser) {
                logger.info("{}", rating.getHotelID());
            }
        }

        // Convert array to list
        assert ratingOfUser != null;
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        // Fetch hotel details for each rating and set the hotel in the rating
        List<Rating> ratingList = ratings.stream().peek(rating -> {
            try {
//                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/get/" + rating.getHotelID(), Hotel.class);
                Hotel hotel = hotelService.getHotel(rating.getHotelID());
                if (hotel != null) {
//                    rating.setHotel(forEntity.getBody());
                    rating.setHotel(hotel);
                    logger.info("Hotel fetched successfully for rating: {}", rating);
                } else {
                    logger.warn("Failed to fetch hotel for rating: {}", rating);
                    rating.setHotel(null); // or handle this scenario as needed
                }
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    logger.warn("Hotel not found with id: {}", rating.getHotelID());
                    rating.setHotel(null);
                } else {
                    logger.error("Error fetching hotel with id: {}", rating.getHotelID(), e);
                    rating.setHotel(null); // or handle other errors as needed
                }
            }
        }).collect(Collectors.toList());

        // Set ratings for the user
        user.setRatings(ratingList);

        return user;
    }

}
