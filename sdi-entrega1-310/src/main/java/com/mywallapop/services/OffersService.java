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

	private Date now = new Date(new java.util.Date().getTime());

	public List<Offer> getOffersCreated() {
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

	public void buyOffer(User activeUser, Long id) {
		

	}

	public Page<Offer> getOffers(Pageable pageable) {
		Page<Offer> offers = offersRepository.findAll(pageable);
		return offers;
	}

	public Page<Offer> searchOffersByTitle(Pageable pageable, String searchText) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText = "%" + searchText + "%";
		offers = offersRepository.searchByTitle(pageable, searchText);
		return offers;
	}

}
