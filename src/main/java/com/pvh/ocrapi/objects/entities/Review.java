package com.pvh.ocrapi.objects.entities;

import lombok.Data;
import lombok.Getter;

@Data
public class Review {
    String reviewId;
    String productId;
    String userId;
    int reviewRating;
    String reviewDescription;

}
