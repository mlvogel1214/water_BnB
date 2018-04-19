package com.mikevogel.waterbnb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mikevogel.waterbnb.models.Listing;

@Repository
public interface ListingRepository extends CrudRepository<Listing, Long> {
	List<Listing> findByAddressContaining(String address);

}
