package com.hosttheworld.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

@GetMapping("/host/new")
public String viewAddApartment(@ModelAttribute("apartment") HostApartment hostApartment) {
	return "addApertment.jsp";
}

@PostMapping("/host/new")
public String addApartment(@Valid @ModelAttribute("apartment") HostApartment hostApartment,Principal principal, BindingResult result,HttpSession session ) {
	if (result.hasErrors()) {
        return "addApertment.jsp";
    } else {
    	String email = principal.getName();
    	User thisUser = userService.findByEmail(email);
    	apartmentService.createApartment(thisUser,hostApartment);
    	return"redirect:/host/new";
    }
 }
}
