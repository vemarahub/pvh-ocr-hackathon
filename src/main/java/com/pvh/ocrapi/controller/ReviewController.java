package com.pvh.ocrapi.controller;

import com.pvh.ocrapi.constants.ReviewConstants;
import com.pvh.ocrapi.objects.entities.Review;
import com.pvh.ocrapi.objects.entities.Users;
import com.pvh.ocrapi.service.ReviewService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
public class ReviewController {


	private static final String CLASSNAME = ReviewController.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	private ReviewService reviewService;

	public ReviewController(ReviewService storeConfigService) {
		this.reviewService = storeConfigService;
	}



	@GetMapping(value = {ReviewConstants.REVIEW_PATH}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Review>> getReviews(
			@PathVariable(value = "productId", required = true) String productId) {

		var response = reviewService.getReview(productId);

		return ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.noCache()).body(response);
	}


	@PostMapping(value = ReviewConstants.REVIEW_PATH)
	public ResponseEntity<Review> createReviews(
			@PathVariable(value = "productId", required = true) String productId,
			@RequestBody(required = true) Review review) {
		var response = reviewService.createReview(review,productId);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.noCache()).body(response);


	}


}
