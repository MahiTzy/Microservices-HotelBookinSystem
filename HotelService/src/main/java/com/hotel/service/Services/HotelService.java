package com.hotel.service.Services;

import com.hotel.service.Models.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);
    Hotel getHotel(String hotelID);
    List<Hotel> getAllHotels();

}
