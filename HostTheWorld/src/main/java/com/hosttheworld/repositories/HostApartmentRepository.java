package com.hosttheworld.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hosttheworld.models.HostApartment;


@Repository
public interface HostApartmentRepository extends CrudRepository<HostApartment, Long> {

	List<HostApartment> findAll();
	
	
}
