package com.mywallapop.services;



import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;
	
	private Date now = new Date(new java.util.Date().getTime());

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

		Set<Offer> user1Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Batidora", "Batidora americana con 3 modos", now, 40.99, user1));
				add(new Offer("Camiseta", "Camiseta de Dior",now, 115.0, user1));
				add(new Offer("IPhone", "No funciona",now, 1.0, user1));
				
			}
		};
		user1.setOffers(user1Offers);
		
		Set<Offer> user2Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Batidora", "sadasdasd",now,  10.0, user2));
				
			}
		};
		user2.setOffers(user2Offers);
		
		Set<Offer> user3Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Batidora", "sadasdasd",now, 10.0, user3));
				
			}
		};
		user3.setOffers(user3Offers);
		
		Set<Offer> user4Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Batidora", "sadasdasd",now, 10.0, user4));
				
			}
		};
		user4.setOffers(user4Offers);
		
		Set<Offer> user5Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Batidora", "sadasdasd",now, 10.0, user5));
				
			}
		};
		user5.setOffers(user5Offers);
		
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(admin);

	}
}