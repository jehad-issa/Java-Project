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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@Table(name="host_apartments")
public class HostApartment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "number of rooms is required!")
    private int numberOfRooms;
    
    @NotNull(message = "Hostage capaity is required!")
    private int hostageCapacity;
    
    @NotNull(message = "Hostage capaity is required!")
    @Positive
    private double price;
    
    @NotEmpty(message = "location is required!")
    private String location;
    
    @NotEmpty(message = "Password is required!")
    private String description;
    
    @Column(columnDefinition = "boolean default false")
    private boolean availability;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    //Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="host_id")
    private User host;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "hosting", 
        joinColumns = @JoinColumn(name = "host_apartment_id"), 
        inverseJoinColumns = @JoinColumn(name = "visitor_hosted_id")
    )
    private List<User> visitors;
    
    
    public HostApartment() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int getHostageCapacity() {
		return hostageCapacity;
	}

	public void setHostageCapacity(int hostageCapacity) {
		this.hostageCapacity = hostageCapacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
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

	public List<User> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<User> visitors) {
		this.visitors = visitors;
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
