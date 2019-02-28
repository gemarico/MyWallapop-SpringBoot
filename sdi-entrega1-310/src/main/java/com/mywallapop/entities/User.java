package com.mywallapop.entities;

import java.util.Set;

import javax.persistence.*;




@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String lastName;
	@Column(unique = true)
	private String email;
	private double credits;
	private String password;
	private String role;
	@Transient
	private String passwordConfirm;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Offer> offersBought;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Offer> offersCreated;

	public User(String name, String lastName, String email) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.credits = 100;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getFullName() {
		if (getLastName() != null)
			return getName() + " " + getLastName();
		else
			return getName();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public void setOffersCreated(Set<Offer> offers) {
		this.offersCreated = offers;
	}

	public Set<Offer> getOffersCreated() {
		return offersCreated;
	}
	
	public void setOffersBought(Set<Offer> offers) {
		this.offersBought = offers;
	}

	public Set<Offer> getOffersBought() {
		return offersBought;
	}

}