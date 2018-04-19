package com.mikevogel.waterbnb.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mikevogel.waterbnb.models.Listing;
import com.mikevogel.waterbnb.models.Review;
import com.mikevogel.waterbnb.service.ListingService;

@Controller
public class ViewController {
	private final ListingService listingService;
	
	public ViewController(ListingService listingService) {
		this.listingService = listingService;

	}
	
	@RequestMapping("/pools/{id}")
	public String viewProp(
			Model model,
			@PathVariable("id") Long id
			) {
		Listing listing = listingService.getListingById(id);
		List<Review> reviews = listing.getReviews();
		model.addAttribute("reviews", reviews);
		model.addAttribute("listing", listing);
		double avg = 0;
		for(int i = 0; i< reviews.size(); i++) {
			int rating = reviews.get(i).getRating();
			avg += rating;
			model.addAttribute("ratingAvg", avg/reviews.size());
		}
		return "viewProp.jsp";
	}

}
