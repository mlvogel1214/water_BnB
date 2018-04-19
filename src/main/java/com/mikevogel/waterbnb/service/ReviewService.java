package com.mikevogel.waterbnb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mikevogel.waterbnb.models.Listing;
import com.mikevogel.waterbnb.models.Review;
import com.mikevogel.waterbnb.models.User;
import com.mikevogel.waterbnb.repositories.ReviewRepository;

@Service
public class ReviewService {
	private ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
		
	}
	
	public List<Review> findAll(){
		return (List<Review>) reviewRepository.findAll();
	}
	public Review addReview(Review review) {
		return reviewRepository.save(review);
	}
	public void addUserToReview(Review review, User user) {
		review.setUser(user);
		reviewRepository.save(review);
	}
	
	public void addListingToReview(Listing listing, Review review) {
		List<Review> newReview= listing.getReviews();
		newReview.add(review);
		listing.setReviews(newReview);
		reviewRepository.save(review);
	}
}
