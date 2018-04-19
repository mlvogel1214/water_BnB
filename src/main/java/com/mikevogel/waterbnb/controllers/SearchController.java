package com.mikevogel.waterbnb.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mikevogel.waterbnb.models.Listing;
import com.mikevogel.waterbnb.service.ListingService;
import com.mikevogel.waterbnb.service.UserService;

@Controller
public class SearchController {
	private final UserService userService;
	private ListingService listingService;
	
	public SearchController(UserService userService, ListingService listingService) {
		this.userService = userService;
		this.listingService = listingService;
	}
	
	@RequestMapping("/")
	public String home(
			@Valid @ModelAttribute("search") Listing listing, 
			BindingResult result
			) {
		
		return "index.jsp";
	}
	
	@RequestMapping("/search/{address}")
	public String searchPage(
			@RequestParam(value="address") String address,
			Principal principal,
			Model model
			) {
		if(principal != null) {
			String username = principal.getName();
			model.addAttribute("currentUser", userService.findByUsername(username));
			model.addAttribute("listings", listingService.findByListingContaining(address));
			return "search.jsp";
		} else {
			model.addAttribute("listings", listingService.findByListingContaining(address));
			return "search.jsp";
		}
	}
	
	@RequestMapping("/search")
	public String defaultPage(
			Principal principal,
			Model model
			) {
		String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
		
		return "search.jsp";
	}
	
}
