package com.rating.service.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_ratings")
public class Rating {
    @Id
    private String ratingID;
    private String userID;
    private String hotelID;
    private int rating;
    private String feedback;
}
