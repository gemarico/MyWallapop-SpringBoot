package com.mywallapop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;


public interface OffersRepository extends CrudRepository<Offer, Long> {

	Page<Offer> findAll(Pageable pageable);
	
	@Query("SELECT r FROM Offer r WHERE (r.flash=true AND r.user <> ?1)")
	Page<Offer> findFlashOffer(Pageable pageable, User user);
	
	@Query("SELECT r FROM Offer r WHERE (r.flash=false and LOWER(r.title) LIKE LOWER(?1) AND r.user <> ?2)")
	Page<Offer> searchByTitle(Pageable pageable, String seachtext, User user);

	@Query("SELECT r FROM Offer r WHERE (r.flash=false and r.user <> ?1)")
	Page<Offer> searchOffersTobuy(Pageable pageable, User user);
	
	
}
