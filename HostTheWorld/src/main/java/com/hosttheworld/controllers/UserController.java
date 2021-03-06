package com.hosttheworld.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hosttheworld.models.Review;
import com.hosttheworld.models.User;
import com.hosttheworld.services.UserService;
import com.hosttheworld.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
    private UserService userService;
    
    
	//Host Registration GET
    @RequestMapping("/host/registration")
    public String registrationForm(@Valid @ModelAttribute("host") User host) {
        return "HostForms.jsp";
    }
   //Host Registration POST
    @PostMapping("/host/registration")
    public String registration(@Valid @ModelAttribute("host") User host, BindingResult result,
    							Model model, HttpSession session) {
        
    	userValidator.validate(host, result);
    	
    	if (result.hasErrors()) {
            return "HostForms.jsp";
        }else if (userService.findAll().size() == 0) {
        	userService.saveWithAdminRole(host);
        	return "redirect:/login";
        }
        userService.saveWithHostRole(host);
        return "redirect:/login";
    }
    
	//Visitor Registration GET
    @RequestMapping("/visitor/registration")
    public String registerForm(@Valid @ModelAttribute("visitor") User visitor) {
        return "VisitorForms.jsp";
    }
   //Visitor Registration POST
    @PostMapping("/visitor/registration")
    public String register(@Valid @ModelAttribute("visitor") User visitor, BindingResult result,
    						Model model, HttpSession session) {
        
    	userValidator.validate(visitor, result);
    	
    	if (result.hasErrors()) {
            return "VisitorForms.jsp";
        }
        userService.saveWithVisitorRole(visitor);
        return "redirect:/login";
    }
    
    //lOGIN
    @RequestMapping("/login")
    public String hostlogin(@RequestParam(value="error", required=false) String error,
    		@RequestParam(value="logout", required=false) String logout, Model model
    		) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid email or password!");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Bye, see you next time..");
        }
        return "LoginPage.jsp";
    }

    
    @RequestMapping(value = {"/"})
    
    public String hostHome(Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        if (currentUser == null) {
        	return "redirect:/login";
        }
		if(currentUser.getRoles().get(0).getName().equals("ROLE_ADMIN")) {
			return "redirect:/admin";
		}
		else if ((currentUser.getRoles().get(0).getName().equals("ROLE_HOST"))) {
			return "redirect:/host/home";
		}else{
			return "redirect:/home";
		}
        
    }
    
    //Visitor Home
    @RequestMapping(value = {"/home"})
    public String visitorHome(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        
        List<User> hosts = userService.findAllHosts();
        
        model.addAttribute("hosts", hosts);
        return "VisitorHome.jsp";
    }
    
    //Favorites
    @RequestMapping(value = {"/favorites"})
    public String favorites(Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser);
        
        List<User> favorites = currentUser.getLikedHosts();
        
        
        model.addAttribute("favorites", favorites);
        return "Favorites.jsp";
    }
    
    //History
    @RequestMapping(value = {"/history"})
    public String history(Principal principal, Model model) {
        String email = principal.getName();
        User currentUser = userService.findByEmail(email);
        model.addAttribute("currentUser", currentUser);
        
        List<Review> reviews = currentUser.getHostReviews();
        HashSet<User> reviewed = new HashSet<>();
        for (Review r: reviews) {
        	reviewed.add(r.getHost());
        }
        
        model.addAttribute("reviewed", reviewed);
        return "History.jsp";
    }
    

}
