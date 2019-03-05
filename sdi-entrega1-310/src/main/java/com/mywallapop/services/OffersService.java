package com.mywallapop.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;
import com.mywallapop.repositories.OffersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;

	public List<Offer> getOffersCreated() {
		List<Offer> offers = new ArrayList<Offer>();
		offersRepository.findAll().forEach(offers::add);
		return offers;
	}

	public Offer getOffer(Long id) {
		return offersRepository.findById(id).get();
	}

	public void addOffer(Offer offer, User activeUser, String id) {
		if (id != null) {
			offer.setFlash(true);
			activeUser.setCredits(activeUser.getCredits() - 20);
		}
		offer.setUser(activeUser);
		offer.setDate(new Date(new java.util.Date().getTime()));
		offersRepository.save(offer);
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}

	public Page<Offer> getOffers(Pageable pageable, User user) {
		Page<Offer> offers = offersRepository.searchOffersTobuy(pageable, user);
		return offers;
	}

	public Page<Offer> getFlashOffers(Pageable pageable, User user) {
		Page<Offer> offers = offersRepository.findFlashOffer(pageable, user);
		return offers;
	}

	public Page<Offer> searchOffersByTitle(Pageable pageable, String searchText, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText = "%" + searchText + "%";
		offers = offersRepository.searchByTitle(pageable, searchText, user);
		return offers;
	}

}
