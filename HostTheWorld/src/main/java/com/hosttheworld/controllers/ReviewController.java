package com.hosttheworld.controllers;

import java.security.Principal;
import java.text.DecimalFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hosttheworld.models.Review;
import com.hosttheworld.models.User;
import com.hosttheworld.services.ReviewService;
import com.hosttheworld.services.UserService;

@Controller
public class ReviewController {
	
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private ReviewService reviewService;
	
	
    // adding new Review "Get Method"
    //Host Details
    @RequestMapping(value = {"/host/details/{hostId}"})
    public String hostDetails(@ModelAttribute("review") Review review , Principal principal, Model model, @PathVariable("hostId") Long id) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        
        User host = userService.findUserById(id);
        model.addAttribute("host", host);
        
        double sum = 0;
        double avg = 0;
        String rank;
        for(Review r: host.getHostReviews()) {
        	sum += r.getHostRank();
        	}
        if (host.getHostReviews().size()==0) {
        	 rank = "unranked yet!";
        }else {
        	avg = sum/host.getHostReviews().size();
            DecimalFormat df = new DecimalFormat("0.0");
            rank = df.format(avg);
        	
        }

        
        model.addAttribute("rank", rank);
        return "HostDetails.jsp";
    }
    // adding new Review "Post Method"
    @PostMapping("/reviews/new/{hostId}")
    public String addCourse(@Valid @ModelAttribute("review") Review review, BindingResult result,
    						Model model, Principal principal, @PathVariable("hostId") Long id) {
        if (result.hasErrors()) {
            String email = principal.getName();
            model.addAttribute("currentUser", userService.findByEmail(email));
            
            User host = userService.findUserById(id);
            model.addAttribute("host", host);
            
            double sum = 0;
            double avg = 0;
            String rank;
            for(Review r: host.getHostReviews()) {
            	sum += r.getHostRank();
            	}
            if (host.getHostReviews().size()==0) {
            	 rank = "unranked yet!";
            }else {
            	avg = sum/host.getHostReviews().size();
            	rank = Double.toString(avg);
            }
            
            model.addAttribute("rank", rank);
            return "HostDetails.jsp";
        } else {
            String email = principal.getName();
            User visitor = userService.findByEmail(email);
        	User host = userService.findUserById(id);
        	reviewService.makeReview(review, visitor, host);
        	return "redirect:/host/details/"+id;
        }
    }
    
    //like a host
    @GetMapping("/like/{hostId}")
    public String like(Model model, Principal principal, @PathVariable("hostId") Long id) {

        String email = principal.getName();
        User visitor = userService.findByEmail(email);
    	User host = userService.findUserById(id);
            
    	reviewService.like(host, visitor);

        	
    	return "redirect:/host/details/"+id;

    }
    
    //Unlike a host
    @GetMapping("/unlike/{hostId}")
    public String unlike(Model model, Principal principal, @PathVariable("hostId") Long id) {

        String email = principal.getName();
        User visitor = userService.findByEmail(email);
    	User host = userService.findUserById(id);
            
    	reviewService.unlike(host, visitor);

        	
    	return "redirect:/host/details/"+id;

    }
    
    //Delete a review:
    @DeleteMapping("/reviews/{id}/{hostId}")
    public String destroy(@PathVariable("id") Long id, @PathVariable("hostId") Long hostId) {
        reviewService.deleteReview(id);
        
        return "redirect:/host/details/"+ hostId;
    }
    

}
