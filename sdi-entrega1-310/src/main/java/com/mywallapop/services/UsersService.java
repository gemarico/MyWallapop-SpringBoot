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
			List<Conversation> convers = conversRepository.findByUser(getUser(Long.parseLong(id)));
			conversRepository.deleteAll(convers);
			usersRepository.delete(getUser(Long.parseLong(id)));
		}

	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

}
