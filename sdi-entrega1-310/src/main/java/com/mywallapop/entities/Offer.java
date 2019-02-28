package com.mywallapop.entities;




import java.sql.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Offer {
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date date;
	private double price;
	private boolean sold;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	

	public Offer(String title, String description, Date date, double price, User user, boolean sold) {
		super();
		this.title = title;
		this.date= date;
		this.description = description;		
		this.price = price;
		this.user = user;
		this.sold= sold;
	}

	public Offer() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	

}