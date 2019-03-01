package com.mywallapop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Offer;
import com.mywallapop.entities.Purchase;
import com.mywallapop.entities.User;
import com.mywallapop.repositories.PurchaseRepository;

@Service
public class PurchaseService {
	
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	public void addPurchase(Purchase purchase) {
		purchaseRepository.save(purchase);
		
	}
	
	public void buyOffer(User user, Offer offer) {
		Purchase purchase = new Purchase(offer, user);
		if(user.getCredits()>=offer.getPrice()) {
			offer.setSold(true);
			user.setCredits(user.getCredits()-offer.getPrice());
			addPurchase(purchase);
		}
			
		
	}

}
