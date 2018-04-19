package com.mikevogel.waterbnb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mikevogel.waterbnb.models.Role;
import com.mikevogel.waterbnb.models.User;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	List<User> findByRolesContains(Role role);
	
}
