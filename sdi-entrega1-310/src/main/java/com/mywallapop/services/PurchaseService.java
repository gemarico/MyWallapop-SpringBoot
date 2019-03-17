package com.mywallapop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Offer;
import com.mywallapop.entities.Purchase;
import com.mywallapop.entities.User;
import com.mywallapop.repositories.OffersRepository;
import com.mywallapop.repositories.PurchaseRepository;
import com.mywallapop.repositories.UsersRepository;

@Service
public class PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	private OffersRepository offersRepository;

	@Autowired
	private UsersRepository usersRepository;

	public void addPurchase(Purchase purchase) {
		purchaseRepository.save(purchase);

	}

	public void buyOffer(User user, Offer offer) {
		Purchase purchase = new Purchase(offer, user);
		if (user.getCredits() >= offer.getPrice() && user.getEmail() != offer.getUser().getEmail()) {
			purchase.getOffer().setSold(true);
			purchase.getBuyer().setCredits(user.getCredits() - offer.getPrice());
			purchase.getBuyer().getPurchased().add(purchase);
			addPurchase(purchase);
			offer.setPurchase(purchase);
			user.getPurchased().add(purchase);
			offersRepository.save(purchase.getOffer());
			usersRepository.save(purchase.getBuyer());

		}
	}

	public List<Purchase> getOffersPurchased(User activeUser) {
		return purchaseRepository.findPurchasedbyUser(activeUser);
	}

}
