package com.hosttheworld.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.hosttheworld.models.HostApartment;
import com.hosttheworld.models.User;
import com.hosttheworld.services.HostApartmentSerivce;
import com.hosttheworld.services.UserService;

@Controller
public class HostApartmentController {
@Autowired
HostApartmentSerivce apartmentService;

@Autowired
UserService userService;

@GetMapping("/hostapartment/new")
public String viewAddApartment(@ModelAttribute("apartment") HostApartment hostApartment) {
	return "addApertment.jsp";
}

@PostMapping("/hostapartment/new")
public String addApartment(@Valid @ModelAttribute("apartment") HostApartment hostApartment, BindingResult result,Principal principal, HttpSession session ) {
	if (result.hasErrors()) {
        return "addApertment.jsp";
    } else {
    	String email = principal.getName();
    	User thisUser = userService.findByEmail(email);
    	apartmentService.createApartment(thisUser,hostApartment);
    	return"redirect:/host/details/"+ thisUser.getId();
    }
 }


	//Edit Apartment Get Method:
	@GetMapping("apartment/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		HostApartment apartment = apartmentService.findAptById(id);
		model.addAttribute("apartment", apartment);
		return "EditApartment.jsp";
	}
	
	//Edit Apartment Put Method:
	@PutMapping("/apartment/{id}")
	public String update(@Valid @ModelAttribute("apartment") HostApartment hostApartment, BindingResult result,
						Principal principal, Model model, @PathVariable("id") Long id) {
	    if (result.hasErrors()) {
	        return "EditApartment.jsp";
	    } else {
	    	String email = principal.getName();
	    	User thisUser = userService.findByEmail(email);
	    	model.addAttribute("currentUser", thisUser);
	    	
	        apartmentService.updateApartment(hostApartment, id);
	        
	        return "redirect:/host/details/"+ thisUser.getId();
	    }
	}
	//book an Apartment Get Method:
	@GetMapping("/book/{aptId}/{hostId}")
	public String Book(@PathVariable("aptId") Long aptId,@PathVariable("hostId") Long hostId, Principal principal) {
    	String email = principal.getName();
    	User thisUser = userService.findByEmail(email);
    	
		HostApartment apartment = apartmentService.findAptById(aptId);
		
		apartmentService.Book(apartment, thisUser);
		
		return "redirect:/host/details/"+ hostId;
	}
	//unbook an Apartment Get Method:
	@GetMapping("/unbook/{aptId}/{hostId}")
	public String unBook(@PathVariable("aptId") Long aptId, @PathVariable("hostId") Long hostId, Principal principal) {
    	String email = principal.getName();
    	User thisUser = userService.findByEmail(email);
    	
		HostApartment apartment = apartmentService.findAptById(aptId);
		
		apartmentService.unBook(apartment, thisUser);
		
		return "redirect:/host/details/"+ hostId;
	}
}
