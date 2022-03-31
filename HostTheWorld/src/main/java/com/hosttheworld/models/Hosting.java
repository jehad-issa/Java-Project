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
@Table(name="hosting")
public class Hosting {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(columnDefinition = "boolean default false")
    private boolean acceptance;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    
    //Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="host_apartment_id")
    private HostApartment hostApartment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="visitor_hosted_id")
    private User visitor;
    
    
    
    public Hosting() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public boolean isAcceptance() {
		return acceptance;
	}

	public void setAcceptance(boolean acceptance) {
		this.acceptance = acceptance;
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

	public HostApartment getHostApartment() {
		return hostApartment;
	}

	public void setHostApartment(HostApartment hostApartment) {
		this.hostApartment = hostApartment;
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
