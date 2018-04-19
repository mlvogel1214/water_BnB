package com.mikevogel.waterbnb.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mikevogel.waterbnb.models.Listing;
import com.mikevogel.waterbnb.models.Review;
import com.mikevogel.waterbnb.models.User;
import com.mikevogel.waterbnb.service.ListingService;
import com.mikevogel.waterbnb.service.ReviewService;
import com.mikevogel.waterbnb.service.UserService;

@Controller
public class ReviewController {
	private final ListingService listingService;
	private ReviewService reviewService;
	private UserService userService;
	
	public ReviewController(ListingService listingService, ReviewService reviewService, UserService userService) {
		this.listingService = listingService;
		this.reviewService = reviewService;
		this.userService = userService;
	}
	
	@RequestMapping("/pools/{id}/review")
	public String review(
			@Valid @ModelAttribute("new") Review review, 
			Model model,
			@PathVariable("id") Long id,
			Principal principal
			) {
		String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
		model.addAttribute("listing", listingService.getListingById(id));
		
		return "review.jsp";
	}
	
	@PostMapping("/add/review/{listingId}/{userId}")
	public String addList(
			@Valid @ModelAttribute("new") Review review, 
			BindingResult result, 
			Model model,
			RedirectAttributes flash,
			@PathVariable("listingId") Long listingId,
			@PathVariable("userId") Long userId
			) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errs", result.getAllErrors());
			return "redirect:/pools/"+listingId+"/review";
		}
		else {
			
			User user = userService.findUserById(userId);
			Review newRev = reviewService.addReview(review);
			Listing listing = listingService.getListingById(listingId);
			reviewService.addUserToReview(newRev, user);
			reviewService.addListingToReview(listing, newRev);
			return "redirect:/pools/"+listingId;
		}
		
	}

}
