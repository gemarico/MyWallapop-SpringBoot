package com.mywallapop.services;

import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Conversation;
import com.mywallapop.entities.Message;
import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;
import com.mywallapop.repositories.ConverRepository;
import com.mywallapop.repositories.MessagesRepository;

@Service
public class ConversationsService {

	@Autowired
	private MessagesRepository messagesRepository;

	@Autowired
	private ConverRepository converRepository;

	public void addMessage(Offer offer, User user, Message message) {
		Conversation conver;

		if (getConverbyOffer(offer, user) != null) {
			conver = getConverbyOffer(offer, user);
		} else {
			conver = new Conversation(user, offer);
		}
		message.setAuthor(user.getFullName());
		message.setConversation(conver);
		message.setDate(new Timestamp(new java.util.Date().getTime()));
		message.setConversation(conver);
		converRepository.save(conver);
		messagesRepository.save(message);

	}

	public List<Message> getMessages(Conversation conversation, User user) {
		List<Message> messages = new ArrayList<Message>();
		messagesRepository.findAllByOrderByIdAsc(conversation, user).forEach(messages::add);
		return messages;
	}

	public List<Conversation> getConversation(User user) {
		List<Conversation> conversations = new ArrayList<Conversation>();
		converRepository.findByUser(user).forEach(conversations::add);
		return conversations;
	}

	public Conversation getConver(Long id) {
		return converRepository.findById(id).get();
	}

	public Conversation getConverbyOffer(Offer offer, User user) {
		return converRepository.findByOffer(offer, user);
	}

	public void delete(Long id) {
		converRepository.delete(getConver(id));

	}

	public void addConver(Conversation conver) {
		converRepository.save(conver);
	}

	public void addMessages(Offer offer, User user, Set<Message> messages) {
		Conversation conver;

		if (getConverbyOffer(offer, user) != null) {
			conver = getConverbyOffer(offer, user);
		} else {
			conver = new Conversation(user, offer);
		}

		for (Message m : messages) {
			m.setConversation(conver);
			m.setConversation(conver);
		}
		conver.setMessages(messages);
		converRepository.save(conver);
		messagesRepository.saveAll(messages);

	}

}
