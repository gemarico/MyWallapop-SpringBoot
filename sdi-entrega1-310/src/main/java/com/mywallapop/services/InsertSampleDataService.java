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
		User user1 = new User("Gema", "Rico Pozas", "gema@email.com");
		user1.setRole("ROLE_CLIENT");
		user1.setPassword("123456");
		
		User user2 = new User("Cristina", "Ruiz de Bucesta", "cristina@email.com");
		user2.setRole("ROLE_CLIENT");
		user2.setPassword("123456");
		
		User user3 = new User("Christian", "Peláez Fernández", "christian@email.com");
		user3.setRole("ROLE_CLIENT");
		user3.setPassword("123456");
		
		User user4 = new User("Javier", "Martínez Álvarez", "javi@email.com");
		user4.setRole("ROLE_CLIENT");
		user4.setPassword("123456");
		
		User user5 = new User("Pedro", "López García", "pedro@email.com");
		user5.setRole("ROLE_CLIENT");
		user5.setPassword("123456");
		
		User admin = new User();
		admin.setPassword("admin");
		admin.setEmail("admin@email.com");
		admin.setName("Admin");
		admin.setRole("ROLE_ADMIN");
			
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(admin);
		
	}
}