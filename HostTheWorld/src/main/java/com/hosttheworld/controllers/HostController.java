package com.hosttheworld.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hosttheworld.models.User;
import com.hosttheworld.services.UserService;

@Controller
public class HostController {


	@Controller
	public class UserController {
		
		@Autowired
	    private UserService userService;
		
	    //Host Home
	    @RequestMapping(value = {"/host/home"})
	    public String visitorHome(Principal principal, Model model) {
	        String email = principal.getName();
	        model.addAttribute("currentUser", userService.findByEmail(email));
	        
	        List<User> hosts = userService.findAllHosts();
	        model.addAttribute("hosts", hosts);
	        return "HostHome.jsp";
	    }
	    
	    @PostMapping(value = {"/search"})
	    public String  search (@RequestParam(value = "search") String search, HttpSession session) {

	        List<User> hosts = userService.findAllHosts();
	        List<User> searchedUsers = userService.findByCountryContain(search);

	        ArrayList<User> result = new ArrayList<>();
	        for (User user: searchedUsers) {
	        	if(hosts.contains(user)) {
	        		result.add(user);
	        	}
	        }
	        session.setAttribute("result", result);
	        session.setAttribute("search", search);
	        return "redirect:/search";
	    }
	    
	    @GetMapping(value = {"/search"})
	    public String  search (Model model, HttpSession session) {
	        
	        model.addAttribute("result", session.getAttribute("result"));
	        model.addAttribute("search", session.getAttribute("search"));
	        return "SearchResult.jsp";
	    }
	    
	    

	}


}
