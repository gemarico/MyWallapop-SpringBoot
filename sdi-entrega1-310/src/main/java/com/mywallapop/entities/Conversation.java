package com.mywallapop.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation {
	@Id
	@GeneratedValue
	private long id;	
	
	@ManyToOne()
	@JoinColumn(name = "s_id" )
	private User sender;
	
	@ManyToOne()
	@JoinColumn(name = "o_id" )
	private Offer offer;
	
	@OneToMany(mappedBy = "conversation", cascade=CascadeType.ALL)
	private Set<Message> messages ;

	
	

	public Conversation(User sender, Offer offer) {
		super();
		this.sender= sender;
		this.offer = offer;
		this.messages = new HashSet<Message>();
		
	}


	public Conversation() {}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Set<Message> getMessages() {
		return messages;
	}


	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}


	public User getSender() {
		return sender;
	}


	public void setSender(User sender) {
		this.sender = sender;
	}


	public Offer getOffer() {
		return offer;
	}


	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	public void addMessage(Message message) {
		
		getMessages().add(message);
	}

	
	

}

