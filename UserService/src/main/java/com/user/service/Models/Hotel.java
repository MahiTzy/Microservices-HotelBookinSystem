package com.user.service.Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    private String hotelID;
    private String hotelName;
    private String hotelAddress;
    private String aboutHotel;
}
