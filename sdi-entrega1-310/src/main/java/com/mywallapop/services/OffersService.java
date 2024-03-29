package com.mywallapop.services;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mywallapop.entities.Conversation;
import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;
import com.mywallapop.repositories.OffersRepository;
import com.mywallapop.repositories.UsersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;

	@Autowired
	private UsersRepository usersRepository;

	public List<Offer> getOffersCreated(User user) {
		return offersRepository.findAllbyUser(user);

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
		activeUser.getOffers().add(offer);
		offersRepository.save(offer);
		usersRepository.save(activeUser);

	}

	public void deleteOffer(Long id) {
		Offer offer = getOffer(id);

		for (Conversation c : offer.getConversations()) {
			c.setMessages(null);
			c.setOffer(null);
			c.setSender(null);
		}
		offer.setConversations(null);

		if (offer.getPurchase() != null) {

			offer.getPurchase().setOffer(null);
			offer.getPurchase().setBuyer(null);
			offer.getUser().getPurchased().remove(offer.getPurchase());

		}
		offer.getUser().getOffers().remove(offer);
		offer.setUser(null);
		offer.setPurchase(null);

		offersRepository.delete(offer);

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

	public void convertFlash(Long id) {
		if (getOffer(id).isFlash() == false && getOffer(id).getUser().getCredits() >= 20.0) {
			getOffer(id).getUser().setCredits(getOffer(id).getUser().getCredits() - 20);
			getOffer(id).setFlash(true);

			offersRepository.save(getOffer(id));
		} else if (getOffer(id).isFlash() == true) {
			getOffer(id).setFlash(false);

			offersRepository.save(getOffer(id));
		}

	}

}
