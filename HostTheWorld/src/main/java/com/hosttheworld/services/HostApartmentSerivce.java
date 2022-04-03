package com.hosttheworld.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosttheworld.models.HostApartment;
import com.hosttheworld.models.User;
import com.hosttheworld.repositories.HostApartmentRepository;

@Service
public class HostApartmentSerivce {
	@Autowired
	HostApartmentRepository hostApartmentRepository;
	
	public List<HostApartment> allApartment(){
		return hostApartmentRepository.findAll();
		}
	
	public void createApartment(User host,HostApartment hostApartment) {
		hostApartment.setHost(host);
		hostApartmentRepository.save(hostApartment);
	}

	//Update
	public void updateApartment( HostApartment hostApartment, Long id) {
		 Optional<HostApartment> optionalHostApartment = hostApartmentRepository.findById(id);
	     if(optionalHostApartment.isPresent()) {
	         HostApartment apt = optionalHostApartment.get();
	         apt.setNumberOfRooms(hostApartment.getNumberOfRooms());
	         apt.setDescription(hostApartment.getDescription());
		     apt.setCountry(hostApartment.getCountry());
		     apt.setCity(hostApartment.getCity());
		     apt.setPrice(hostApartment.getPrice());
		     apt.setLatitude(hostApartment.getLatitude());
		     apt.setLongitude(hostApartment.getLongitude());
		     apt.setImgUrl(hostApartment.getImgUrl());
	     	 
		     hostApartmentRepository.save(apt);
	     }
		
	}

	public HostApartment findAptById(Long id) {
		 Optional<HostApartment> optionalHostApartment = hostApartmentRepository.findById(id);
	     if(optionalHostApartment.isPresent()) {
	    	 HostApartment apt = optionalHostApartment.get();
	    	 return apt;
	     }
	     return null;
	     
	}

	public void Book(HostApartment apartment, User visitor) {
		apartment.getVisitors().add(visitor);
		hostApartmentRepository.save(apartment);
		
	}

	public void unBook(HostApartment apartment, User visitor) {
		apartment.getVisitors().remove(visitor);
		hostApartmentRepository.save(apartment);
	}

}
