package com.mywallapop.entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private long id;
	private String text;
	private String author;

	private Timestamp date;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;

	public Message(String author, String text, Timestamp date) {
		super();
		this.author = author;
		this.text = text;
		this.date = date;

	}

	public Message() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTime() {
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(getDate());
	}

}
