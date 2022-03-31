package com.hosttheworld.controllers;

import java.security.Principal;

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
    

    @RequestMapping(value = {"/","/home"})
    
    public String hostHome(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        return "homePage.jsp";
    }
    

}
