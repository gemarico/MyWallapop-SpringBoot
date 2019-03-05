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
import com.mywallapop.entities.Purchase;
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

				add(new Offer("IPhone", "No funciona", convertDate("2019-01-20"), 1.0, user1));
				add(new Offer("Camiseta", "Camiseta de Dior", convertDate("2018-01-04"), 115.0, user1));
				add(new Offer("Batidora", "Batidora americana con 3 modos", convertDate("2018-08-25"), 40.99, user1));

			}
		};
		user1.setOffers(user1Offers);

		Set<Offer> user2Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Coche", "Coche nuevo", convertDate("2018-05-16"), 100.0, user2));
				add(new Offer("Libro", "Harry Potter y el cáliz de fuego", convertDate("2018-05-16"), 5.0, user2));
				add(new Offer("Botella", "Ecológica", convertDate("2018-05-16"), 4.0, user2));

			}
		};
		user2.setOffers(user2Offers);

		Set<Offer> user3Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{

				add(new Offer("Set Maquillaje", "Maybelline", convertDate("2017-03-27"), 40.0, user3));
				add(new Offer("Marco", "para fotos", convertDate("2017-03-27"), 10.0, user3));
				add(new Offer("Nevera", "Sin usar", convertDate("2017-03-27"), 500.0, user3));

			}
		};
		user3.setOffers(user3Offers);

		Set<Offer> user4Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Offer("Casco de moto", "regulable", convertDate("2019-02-14"), 700.0, user4));
				add(new Offer("Lámpara", "sin bombilla", convertDate("2019-02-14"), 15.0, user4));
				add(new Offer("Play 4", "con dos mandos", convertDate("2019-02-14"), 200.0, user4));

			}
		};
		user4.setOffers(user4Offers);

		Set<Offer> user5Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{

				add(new Offer("Champú", "anticaspa", convertDate("2018-05-08"), 1.0, user5));
				add(new Offer("Velas", "con aroma a vainilla", convertDate("2018-05-08"), 10.0, user5));
				add(new Offer("Guitarra", "acoústica", convertDate("2018-05-08"), 30.0, user5));

			}
		};
		user5.setOffers(user5Offers);
		
		
		


		Offer offer1 = getOffer(user1);
		Set<Purchase> purchased1 = new HashSet<Purchase>();
		Purchase purchase1 = new Purchase(offer1, user4);
		purchased1.add(purchase1);
		user4.setPurchased(purchased1);
		offer1.setSold(true);
		

		Offer offer2 = getOffer(user2);
		Set<Purchase> purchased2 = new HashSet<Purchase>();
		Purchase purchase2 = new Purchase(offer2, user5);
		purchased2.add(purchase2);
		user5.setPurchased(purchased2);
		offer2.setSold(true);
		

		Offer offer3 = getOffer(user3);
		Set<Purchase> purchased3 = new HashSet<Purchase>();
		Purchase purchase3 = new Purchase(offer3, user1);
		purchased1.add(purchase3);
		offer3.setSold(true);
		user1.setPurchased(purchased3);
		

		Offer offer4 = getOffer(user4);
		Set<Purchase> purchased4 = new HashSet<Purchase>();
		Purchase purchase4 = new Purchase(offer4, user2);
		purchased2.add(purchase4);
		user2.setPurchased(purchased4);
		offer4.setSold(true);
		
		
		usersService.addUser(user1,(100 - offer3.getPrice()));
		usersService.addUser(user2,(100 - offer4.getPrice()));
		usersService.addUser(user3,100);
		usersService.addUser(user4,(100 - offer1.getPrice()));
		usersService.addUser(user5,(100 - offer2.getPrice()));
		usersService.addUser(admin,0);
		
		

		
	}

	private Date convertDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = sdf.parse(date);
		Date sqlDate = new Date(d.getTime());
		return sqlDate;
	}

	private Offer getOffer(User user) {

		for (Offer o : user.getOffers()) {
			if (o.getPrice() < 100)
				return o;
		}

		return null;
	}

}