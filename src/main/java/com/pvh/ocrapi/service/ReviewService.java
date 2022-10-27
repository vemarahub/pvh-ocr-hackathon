package com.pvh.ocrapi.service;

import com.pvh.ocrapi.objects.entities.Review;
import com.pvh.ocrapi.objects.entities.Users;
import com.pvh.ocrapi.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private static final String CLASSNAME = ReviewService.class.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASSNAME);

    private final ReviewRepository reviewRepository;

    private final MongoTemplate mongoTemplate;



    public List<Review> getReview(String productId) {
        List<Review> reviews =  reviewRepository.findByProductId(productId);

       return reviews;
    }


    public Review createReview(Review review,String productId) {
        review.setProductId(productId);
        Review reviewSaved = reviewRepository.save(review);
        return reviewSaved;
    }




}
