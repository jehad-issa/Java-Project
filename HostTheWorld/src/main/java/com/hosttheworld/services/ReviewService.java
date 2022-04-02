package com.hosttheworld.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosttheworld.models.Review;
import com.hosttheworld.models.User;
import com.hosttheworld.repositories.ReviewRepository;
import com.hosttheworld.repositories.UserRepository;

 
@Service
public class ReviewService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private ReviewRepository reviewRepository;
	


    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	
	public List<Review> findAll() {
		
		return reviewRepository.findAll();
	}
	
	 // create a review
	 public void makeReview(Review review, User visitor, User host) {
		 Review thisReview = review;
		 thisReview.setHost(host);
		 thisReview.setReviewer(visitor);
		 reviewRepository.save(thisReview);
	 }


	 //Delete the review
	 public void deleteReview(Long id) {
		reviewRepository.deleteById(id);
	}

	 
	 //Like a host
	public void like(User host, User visitor) {
		User thisHost = host;
		User thisVisitor = visitor;
		thisVisitor.getLikedHosts().add(thisHost);
		userRepository.save(thisVisitor);
		
	}
	
	 //unLike a host
	public void unlike(User host, User visitor) {
		User thisHost = host;
		User thisVisitor = visitor;
		thisVisitor.getLikedHosts().remove(thisHost);
		userRepository.save(thisVisitor);
		
	}
}

