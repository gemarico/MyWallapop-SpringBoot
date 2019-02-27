package com.mywallapop.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;
import com.mywallapop.repositories.OffersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;
	
	private Date now = new Date(new java.util.Date().getTime());

	public List<Offer> getOffers() {
		List<Offer> offers = new ArrayList<Offer>();
		offersRepository.findAll().forEach(offers::add);
		return offers;
	}

	public Offer getOffer(Long id) {
		return offersRepository.findById(id).get();
	}

	public void addOffer(Offer offer, User activeUser) {
		offer.setUser(activeUser);		
		offer.setDate(now);		
		offersRepository.save(offer);
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}

}
