package com.mikevogel.waterbnb.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mikevogel.waterbnb.models.Listing;
import com.mikevogel.waterbnb.service.ListingService;

@Controller
public class EditController {
	
	private final ListingService listingService;
	
	public EditController(ListingService listingService) {
		this.listingService = listingService;
	}
	
	@RequestMapping("/edit/listing/{id}")
	public String edit(
			Model model,
			@PathVariable("id") Long id,
			@Valid @ModelAttribute("new") Listing listing, 
			BindingResult result
			) {
		
		Listing listing1 = listingService.getListingById(id);
		model.addAttribute("listing", listing1);
		model.addAttribute("reviews", listing1.getReviews());
		return "edit.jsp";
	}
	@PostMapping("/edit/listing/{id}")
	public String editListing(
			@PathVariable("id") Long id,
			@Valid @ModelAttribute("new") Listing listing, 
			BindingResult result
			) {
		listingService.updateListing(listing);
		
		return "redirect:/host/dashboard";
	}

}
