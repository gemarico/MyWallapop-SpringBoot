package com.mywallapop.services;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Conversation;
import com.mywallapop.entities.Offer;
import com.mywallapop.entities.Purchase;
import com.mywallapop.entities.User;
import com.mywallapop.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private OffersService offersService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	public List<User> getUsers(User user) {

		return usersRepository.findUsers(user);
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (user.getEmail() != "admin@email.com")
			user.setRole("ROLE_CLIENT");
		usersRepository.save(user);

	}

	public void deleteUser(String[] ids) {
		for (String id : ids) {
			User user = getUser(Long.parseLong(id));

			for (Offer o : user.getOffers()) {
				Offer offer = offersService.getOffer(o.getId());

				for (Conversation c : offer.getConversations()) {
					c.setMessages(null);
					c.setOffer(null);
					c.setSender(null);
				}
				offer.setConversations(null);

				if (offer.getPurchase() != null) {

					offer.getPurchase().setOffer(null);
					offer.getUser().getPurchased().remove(offer.getPurchase());

				}
				offer.setUser(null);
				offer.setPurchase(null);
			}

			user.setOffers(null);

			for (Conversation c : user.getConversations()) {
				c.setMessages(null);
				c.setOffer(null);
				c.setSender(null);
			}
			user.setConversations(null);
			if (user.getPurchased() != null) {
				for (Purchase p : user.getPurchased()) {
					p.setBuyer(null);
					p.setOffer(null);
				}
			}
			user.setPurchased(null);

			usersRepository.delete(user);
		}
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

}
