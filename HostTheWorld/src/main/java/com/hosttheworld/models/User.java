package com.hosttheworld.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    
    @Size(min = 1 , message = "Name is required!")
    private String name;
    
//    @NotEmpty(message = "email is required!")
    @Size(min = 1 , message = "Email is required!")
    private String email;
    
    @Size(min = 1 , message = "please Enter your Phone number")
    private String phoneNumber;
    
    @Size(min = 8 , message = "Password must be 8 characters at least")
    private String password;
    
    @Size(min = 8 , message = "Password confirmation is required!")
    @Transient
    private String passwordConfirmation;
    
    @Size(min = 1 , message = "Country is required!")
    private String country;
    
    @Size(min = 1 , message = "City is required!")
    private String city;
    
    @URL
    private String imgUrl;
    
    
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles", 
        joinColumns = @JoinColumn(name = "host_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    
    //Relationships with host apartments table
    @OneToMany(mappedBy="host", fetch = FetchType.EAGER)
    private List<HostApartment> hostApartments;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "hosting", 
        joinColumns = @JoinColumn(name = "visitor_hosted_id"), 
        inverseJoinColumns = @JoinColumn(name = "host_apartment_id"))
    private List<HostApartment> apartmentsToVisit;
    
    //Relationships with visitors Same Table(Self Join)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "favorites", 
        joinColumns = @JoinColumn(name = "host_id"), 
        inverseJoinColumns = @JoinColumn(name = "visitor_id"))
    private List<User> fans;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "favorites", 
        joinColumns = @JoinColumn(name = "visitor_id"), 
        inverseJoinColumns = @JoinColumn(name = "host_id"))
    private List<User> likedHosts;
   
    //relationships with reviews table
    @OneToMany(mappedBy="host", fetch = FetchType.LAZY)
    private List<Review> hostReviews;
    
    @OneToMany(mappedBy="reviewer", fetch = FetchType.LAZY)
    private List<Review> visitorReviews;
    
    public User() {
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public List<Review> getHostReviews() {
		return hostReviews;
	}

	public void setHostReviews(List<Review> hostReviews) {
		this.hostReviews = hostReviews;
	}

	public List<Review> getVisitorReviews() {
		return visitorReviews;
	}

	public void setVisitorReviews(List<Review> visitorReviews) {
		this.visitorReviews = visitorReviews;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public List<HostApartment> getHostApartments() {
		return hostApartments;
	}

	public void setHostApartments(List<HostApartment> hostApartments) {
		this.hostApartments = hostApartments;
	}
	
	public List<User> getFans() {
		return fans;
	}

	public void setFans(List<User> fans) {
		this.fans = fans;
	}

	public List<User> getLikedHosts() {
		return likedHosts;
	}

	public void setLikedHosts(List<User> likedHosts) {
		this.likedHosts = likedHosts;
	}

	public List<HostApartment> getApartmentsToVisit() {
		return apartmentsToVisit;
	}

	public void setApartmentsToVisit(List<HostApartment> apartmentsToVisit) {
		this.apartmentsToVisit = apartmentsToVisit;
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
