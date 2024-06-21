package com.hotel.service.Services.Impl;

import com.hotel.service.Exceptions.ResourceNotFoundException;
import com.hotel.service.Models.Hotel;
import com.hotel.service.Respositories.HotelRepo;
import com.hotel.service.Services.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;

    HotelServiceImpl(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }
    @Override
    public Hotel createHotel(Hotel hotel) {
        String uuid = UUID.randomUUID().toString();
        hotel.setHotelID(uuid);
        return hotelRepo.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelID) {
        return hotelRepo.findById(hotelID).orElseThrow(()->
                new ResourceNotFoundException("Hotel not found"));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }
}
