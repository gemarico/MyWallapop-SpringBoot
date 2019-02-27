package com.mywallapop.services;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.User;
import com.mywallapop.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

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
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCredits(100);
		usersRepository.save(user);
	}	
	
	public void deleteUser(String[] ids) {
		for(String id : ids){
			usersRepository.deleteById(Long.parseLong(id));
		}
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
}
