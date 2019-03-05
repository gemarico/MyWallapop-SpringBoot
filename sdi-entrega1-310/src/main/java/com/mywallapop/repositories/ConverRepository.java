package com.mywallapop.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mywallapop.entities.Conversation;
import com.mywallapop.entities.Offer;
import com.mywallapop.entities.User;

public interface ConverRepository extends CrudRepository<Conversation, Long> {

	@Query("SELECT r FROM Conversation r WHERE ((r.sender = ?2 or r.offer.user = ?2) and r.offer= ?1 ) ORDER BY r.id ASC")
	Conversation findByOffer(Offer offer, User user);

	@Query("SELECT r FROM Conversation r WHERE (r.sender = ?1 or r.offer.user = ?1) ORDER BY r.id ASC")
	List<Conversation> findByUser(User user);
	
	

}
