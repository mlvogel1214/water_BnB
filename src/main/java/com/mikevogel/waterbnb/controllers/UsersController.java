package com.mikevogel.waterbnb.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mikevogel.waterbnb.models.Listing;
import com.mikevogel.waterbnb.models.User;
import com.mikevogel.waterbnb.service.ListingService;
import com.mikevogel.waterbnb.service.UserService;
import com.mikevogel.waterbnb.validator.UserValidator;

@Controller
public class UsersController {
	
	private UserService userService;
	private UserValidator userValidator;
	private ListingService listingService;
    
    public UsersController(UserService userService, UserValidator userValidator, ListingService listingService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.listingService = listingService;
    }
	
	@PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes, @RequestParam("role") String role, Error error) {
		userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "login.jsp";
        }
        if(role.equals("HOST")){
            userService.saveUserWithHostRole(user);
        }else{
            userService.saveWithGuestRole(user);
        }
        
        redirectAttributes.addFlashAttribute("regMessage", "Registration Succesful! Please Login with your credentials");
        return "login.jsp";
    }
	
	@RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @Valid @ModelAttribute("user") User user, @ModelAttribute("regMessage") String regMessage) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "login.jsp";
    }
	
	@RequestMapping("/host/dashboard")
    public String adminPage(
    		Principal principal, 
    		Model model,
    		@Valid @ModelAttribute("new") Listing listing,
    		BindingResult result
    		) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        List<Listing> listings = listingService.getAll();
        model.addAttribute("listings", listings);
        
        return "dashboard.jsp";
    }
	
	@RequestMapping("/delete/{id}")
	public String delete(
			@PathVariable("id") Long id
			) {
		userService.delete(id);
		return "redirect:/admin";
		
	}
	
	@RequestMapping("/add/admin/{id}")
	public String addAdmin(
			@PathVariable("id") Long id
			) {
		User user = userService.findUserById(id);
		userService.makeUserAdmin(user);
		return "redirect:/admin";
	}
	
	@RequestMapping(value = {"/test"})
    public String home(
    		Principal principal, 
    		Model model,
    		@ModelAttribute("new") Listing listing1,
    		@Valid @ModelAttribute("search") Listing listing, 
			BindingResult result
    		) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser", userService.findByUsername(username));
        System.out.println(user.getRoles().get(0).getName());
        if(user.getRoles().get(0).getName().equals("ROLE_HOST")) {
        	return "redirect:/host/dashboard";
        }
        return "redirect:/search";

    }
}
