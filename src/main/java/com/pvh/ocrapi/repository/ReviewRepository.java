package com.pvh.ocrapi.repository;

import com.pvh.ocrapi.objects.entities.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query
    List<Review> findByProductId(String productId);

}
