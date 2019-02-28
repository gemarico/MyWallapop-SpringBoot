package com.mywallapop.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	
	@PostConstruct
	public void init() throws ParseException {

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
				add(new Offer("Batidora", "Batidora americana con 3 modos",  convertDate("2018-08-25") , 40.99, user1,true));
				add(new Offer("Camiseta", "Camiseta de Dior", convertDate("2018-01-04"), 115.0, user1,false));
				add(new Offer("IPhone", "No funciona", convertDate("2019-01-20"), 1.0, user1,false));

			}
		};
		user1.setOffersCreated(user1Offers);

		Set<Offer> user2Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Batidora", "sadasdasd", convertDate("2018-05-16"), 10.0, user2,true));

			}
		};
		user2.setOffersCreated(user2Offers);

		Set<Offer> user3Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Batidora", "sadasdasd", convertDate("2017-03-27"), 10.0, user3,false));

			}
		};
		user3.setOffersCreated(user3Offers);

		Set<Offer> user4Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Batidora", "sadasdasd", convertDate("2019-02-14"), 10.0, user4,false));

			}
		};
		user4.setOffersCreated(user4Offers);

		Set<Offer> user5Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Batidora", "sadasdasd", convertDate("2018-05-08"), 10.0, user5,false));

			}
		};
		user5.setOffersCreated(user5Offers);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(admin);

	}
	
	private Date convertDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = sdf.parse(date);
		Date sqlDate = new Date(d.getTime());
		return sqlDate;
	}

}