package com.hosttheworld.services;

import java.util.List;


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
//	public Student findStudent(Long id) {
//	    Optional<Student> j =studentRepo.findById(id);
//	    if(j.isPresent()) {
//	    	return j.get();
//	    }else {
//	    	return null;
//	    	}
//	   }
}
