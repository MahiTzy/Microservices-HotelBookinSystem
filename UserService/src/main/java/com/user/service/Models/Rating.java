package com.user.service.Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    private String ratingID;
    private String userID;
    private String hotelID;
    private int rating;
    private String feedback;

    private Hotel hotel;

}
