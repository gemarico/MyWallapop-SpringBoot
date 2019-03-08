package com.mywallapop.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Message;
import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;
import com.mywallapop.repositories.ConverRepository;
import com.mywallapop.repositories.MessagesRepository;
import com.mywallapop.repositories.OffersRepository;
import com.mywallapop.repositories.PurchaseRepository;
import com.mywallapop.repositories.UsersRepository;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;

	@Autowired
	private OffersService offersService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ConversationsService converService;
	
	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private OffersRepository offersRepo;

	@Autowired
	private PurchaseRepository purchaseRepo;

	@Autowired
	private ConverRepository converRepo;
	
	@Autowired
	private MessagesRepository mesagesRepo;

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

		Offer offer1 = new Offer("IPhone", "No funciona", convertDate("2019-01-20"), 1.0, user1);
		Offer offer2 = new Offer("Camiseta", "Camiseta de Dior", convertDate("2018-01-04"), 20.99, user1);
		Offer offer3 = new Offer("Lavavajillas", "Fagor", convertDate("2018-07-21"), 30.5, user1);

		Offer offer4 = (new Offer("Coche de jueguete", "playmobil", convertDate("2018-05-16"), 5.0, user2));
		Offer offer5 = (new Offer("Libro", "Harry Potter y el cáliz de fuego", convertDate("2018-05-16"), 5.0, user2));
		Offer offer6 = (new Offer("Botella", "Ecológica", convertDate("2018-05-16"), 4.0, user2));

		Offer offer7 = (new Offer("Set Maquillaje", "Maybelline", convertDate("2017-03-27"), 40.0, user3));
		Offer offer8 = (new Offer("Marco", "para fotos", convertDate("2017-03-27"), 10.0, user3));
		Offer offer9 = (new Offer("Funda móvil", "Sin usar", convertDate("2017-03-27"), 10.0, user3));

		Offer offer10 = (new Offer("Casco de moto", "regulable", convertDate("2019-02-14"), 700.0, user4));
		Offer offer11 = (new Offer("Lámpara", "sin bombilla", convertDate("2019-02-14"), 15.0, user4));
		Offer offer12 = (new Offer("Play 4", "con dos mandos", convertDate("2019-02-14"), 200.0, user4));

		Offer offer13 = (new Offer("Champú", "anticaspa", convertDate("2018-05-08"), 100.0, user5));
		Offer offer14 = (new Offer("Velas", "con aroma a vainilla", convertDate("2018-05-08"), 10.0, user5));
		Offer offer15 = (new Offer("Guitarra", "acoústica", convertDate("2018-05-08"), 30.0, user5));

		Set<Offer> user1Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{

				add(offer1);
				add(offer2);
				add(offer3);

			}
		};
		user1.setOffers(user1Offers);

		Set<Offer> user2Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{

				add(offer4);
				add(offer5);
				add(offer6);

			}
		};
		user2.setOffers(user2Offers);

		Set<Offer> user3Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{

				add(offer7);
				add(offer8);
				add(offer9);
			}
		};
		user3.setOffers(user3Offers);

		Set<Offer> user4Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{

				add(offer10);
				add(offer11);
				add(offer12);

			}
		};
		user4.setOffers(user4Offers);

		Set<Offer> user5Offers = new HashSet<Offer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{

				add(offer13);
				add(offer14);
				add(offer15);

			}
		};
		user5.setOffers(user5Offers);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(admin);

		offersService.addOffer(offer1, user1, null);
		offersService.addOffer(offer2, user1, "flash");
		offersService.addOffer(offer3, user1, null);
		offersService.addOffer(offer4, user2, "flash");
		offersService.addOffer(offer5, user2, null);
		offersService.addOffer(offer6, user2, null);
		offersService.addOffer(offer7, user3, "flash");
		offersService.addOffer(offer8, user3, null);
		offersService.addOffer(offer9, user3, null);
		offersService.addOffer(offer10, user4, "flash");
		offersService.addOffer(offer11, user4, null);
		offersService.addOffer(offer12, user4, null);
		offersService.addOffer(offer13, user5, "flash");
		offersService.addOffer(offer14, user5, null);
		offersService.addOffer(offer15, user5, null);

		createConvers(offer1, user2);
		createConvers(offer2, user4);
		createConvers(offer3, user5);
		createConvers(offer4, user1);
		createConvers(offer5, user3);
		createConvers(offer6, user4);
		createConvers(offer7, user3);
		createConvers(offer8, user1);
		createConvers(offer9, user2);
		createConvers(offer10, user4);
		createConvers(offer11, user1);
		createConvers(offer12, user3);
		createConvers(offer13, user3);
		createConvers(offer14, user1);
		createConvers(offer15, user4);

		// compra oferta 4 el user 1
		purchaseService.buyOffer(user1, offer4);

		// compra oferta 5 el user 1
		purchaseService.buyOffer(user1, offer5);

		// compra oferta 1 el user 2
		purchaseService.buyOffer(user2, offer1);
		// compra oferta 2 el user 2
		purchaseService.buyOffer(user2, offer14);

		// compra oferta 3 el user 3
		purchaseService.buyOffer(user3, offer11);
		// compra oferta 6 el user 3
		purchaseService.buyOffer(user3, offer15);

		// compra oferta 7 el user 4
		purchaseService.buyOffer(user4, offer7);
		// compra oferta 8 el user 4
		purchaseService.buyOffer(user4, offer2);

		// compra oferta 9 el user 5
		purchaseService.buyOffer(user5, offer9);
		// compra oferta 10 el user 5
		purchaseService.buyOffer(user5, offer10);

	}

	private void createConvers(Offer offer, User sender) {

		String[] texts = { "Hola estoy interesado en su producto", "Hola", "El precio no es negociable", "Que caro!",
				"Funciona?" };
		Set<Message> messages = new HashSet<Message>();
		Message message1 = new Message(sender.getFullName(), texts[(int) (Math.random() * texts.length)], getTime());
		Message message2 = new Message(offer.getUser().getFullName(), texts[(int) (Math.random() * texts.length)],
				getTime());
		Message message3 = new Message(sender.getFullName(), texts[(int) (Math.random() * texts.length)], getTime());
		Message message4 = new Message(offer.getUser().getFullName(), texts[(int) (Math.random() * texts.length)],
				getTime());
		messages.add(message1);
		messages.add(message2);
		messages.add(message3);
		messages.add(message4);
		converService.addMessages(offer, sender, messages);

	}

	private Date convertDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = sdf.parse(date);
		Date sqlDate = new Date(d.getTime());
		return sqlDate;
	}

	public Timestamp getTime() {
		return new Timestamp(new java.util.Date().getTime());
	}
	
	public void deleteBBDD() {
		usersRepo.deleteAll();
		offersRepo.deleteAll();
		purchaseRepo.deleteAll();
		converRepo.deleteAll();
		mesagesRepo.deleteAll();
	}

}