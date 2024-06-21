package com.user.service.External.Services;

import com.user.service.Models.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {
    @GetMapping("/hotels/get/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId);
}
