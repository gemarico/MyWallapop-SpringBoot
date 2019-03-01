package com.mywallapop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Purchase {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;
	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private User buyer;
	
	public Purchase(Offer offer, User buyer) {
		super();
		this.offer = offer;
		this.buyer = buyer;
	}

	
	public Purchase() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Offer getOffer() {
		return offer;
	}


	public void setOffer(Offer offer) {
		this.offer = offer;
	}


	public User getBuyer() {
		return buyer;
	}


	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}


	public String getTitle() {
		return offer.getTitle();
	}

	public double getPrice() {
		return offer.getPrice();
	}

	public String getDescription() {
		return offer.getDescription();
	}

	public String getEmail() {
		return buyer.getEmail();
	}
	
	
	
	
	
	
	
}
