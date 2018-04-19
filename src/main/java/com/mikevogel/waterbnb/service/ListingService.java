package com.mikevogel.waterbnb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mikevogel.waterbnb.models.Listing;
import com.mikevogel.waterbnb.models.User;
import com.mikevogel.waterbnb.repositories.ListingRepository;

@Service
public class ListingService {
	private final ListingRepository listingRepository;
	
	public ListingService(ListingRepository listingRepository) {
		this.listingRepository = listingRepository;
	}
	
	public List<Listing> getAll(){
		return (List<Listing>) listingRepository.findAll();
	}
	public Listing addListing(Listing listing) {
		return listingRepository.save(listing);
	}
	public void addUserToListing(Listing listing, User user) {
		listing.setUser(user);
		listingRepository.save(listing);
	}
	
	public Listing getListingById(Long id) {
		return listingRepository.findById(id).orElse(null);
	}
	
	public void updateListing(Listing listing) {
    	listingRepository.save(listing);
    }
	public List<Listing> findByListingContaining(String address){
		return listingRepository.findByAddressContaining(address);
	}
}
