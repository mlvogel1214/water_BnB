package com.mikevogel.waterbnb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mikevogel.waterbnb.models.Review;
@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

}
