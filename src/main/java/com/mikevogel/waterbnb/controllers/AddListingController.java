package com.mikevogel.waterbnb.controllers;



import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mikevogel.waterbnb.models.Listing;
import com.mikevogel.waterbnb.models.User;
import com.mikevogel.waterbnb.service.ListingService;
import com.mikevogel.waterbnb.service.UserService;

@Controller
public class AddListingController {
	private final ListingService listingService;
	private UserService userService;
	
	public AddListingController(ListingService listingService, UserService userService) {
		this.listingService = listingService;
		this.userService = userService;
	}
	
	@PostMapping("/add/listing/{id}")
	public String addList(
			@Valid @ModelAttribute("new") Listing listing, 
			BindingResult result, 
			Model model,
			RedirectAttributes flash,
			@PathVariable("id") Long id
			) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errs", result.getAllErrors());
			return "redirect:/dashboard";
		}
		else {
			User user = userService.findUserById(id);
			Listing list1 = listingService.addListing(listing);
			listingService.addUserToListing(list1, user);
			return "redirect:/host/dashboard";
		}
		
	}

}
