package com.hotel.service.Controllers;

import com.hotel.service.Models.Hotel;
import com.hotel.service.Services.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;
    HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

//    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.createHotel(hotel));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

//    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/get/{hotelID}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelID) {
        return ResponseEntity.ok(hotelService.getHotel(hotelID));
    }
}
