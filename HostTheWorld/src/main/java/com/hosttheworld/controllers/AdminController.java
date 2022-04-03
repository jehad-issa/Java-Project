package com.hosttheworld.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hosttheworld.models.Role;
import com.hosttheworld.models.User;
import com.hosttheworld.services.RoleService;
import com.hosttheworld.services.UserService;

@Controller
public class AdminController {
	

	
	@Autowired
    private UserService userService;
	
	@Autowired
    private RoleService roleService;
    
    
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "AdminPage.jsp";
    }
    
    //Delete a User:
    @DeleteMapping("/users/{id}")
    public String destroy(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    
    //make a user Admin
    @GetMapping("/user/{id}")
    public String makeAdmin(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        List<Role> role = roleService.findRoleByName("ROLE_ADMIN");
        userService.makeUserAdmin(user, role);
        return "redirect:/admin";
    }
    //make an admin as host
    @GetMapping("/makehost/{id}")
    public String makeHost(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        List<Role> role = roleService.findRoleByName("ROLE_HOST");
        userService.makeUserAdmin(user, role);
        return "redirect:/admin";
    }
}
