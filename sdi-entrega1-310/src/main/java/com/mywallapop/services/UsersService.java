package com.mywallapop.services;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Conversation;
import com.mywallapop.entities.User;
import com.mywallapop.repositories.ConverRepository;
import com.mywallapop.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ConverRepository conversRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	public List<User> getUsers(User user) {
		List<User> users = new ArrayList<User>();
		if (user.getRole().equals("ROLE_ADMIN")) {
			usersRepository.findAll().forEach(users::add);
		}
		users.remove(getUserByEmail("admin@email.com"));
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user, double credits) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCredits(credits);
		usersRepository.save(user);
		
	}

	public void deleteUser(String[] ids) {
		for (String id : ids) {
			List<Conversation> convers = conversRepository.findByUser(getUser(Long.parseLong(id)));
			conversRepository.deleteAll(convers);
			usersRepository.delete(getUser(Long.parseLong(id)));
		}

	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

}
