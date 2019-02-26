package com.mywallapop.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.User;

@Service
public class InsertSampleDataService {
	
	
	
	@Autowired
	private UsersService usersService;

	@PostConstruct
	public void init() {
		User user1 = new User("Gema", "Rico Pozas", "gemarico@outlook.it");
		user1.setPassword("123456");
		
			
		
		usersService.addUser(user1);
		
	}
}