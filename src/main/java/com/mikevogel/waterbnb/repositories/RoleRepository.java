package com.mikevogel.waterbnb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mikevogel.waterbnb.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	 List<Role> findAll();
	 
	 List<Role> findByName(String name);
}
