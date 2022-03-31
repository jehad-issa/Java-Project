package com.hosttheworld.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hosttheworld.models.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	List<Role> findByName(String string);
	
}