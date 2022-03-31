package com.hosttheworld.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;


@Entity
@Table(name="reviews_table")
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    

    private String hostReview;
    
    private double hostRank;

    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    
    //Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="host_review_id")
    private User host;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reviewer_id")
    private User reviewer;
    
    public Review() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getHostReview() {
		return hostReview;
	}

	public void setHostReview(String hostReview) {
		this.hostReview = hostReview;
	}

	public double getHostRank() {
		return hostRank;
	}

	public void setHostRank(double hostRank) {
		this.hostRank = hostRank;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public User getReviewer() {
		return reviewer;
	}

	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
