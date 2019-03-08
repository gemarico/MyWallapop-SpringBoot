package com.mywallapop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mywallapop.entities.Purchase;
import com.mywallapop.entities.User;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
	
	@Query("SELECT r FROM Purchase r WHERE (r.offer.sold = true and r.buyer = ?1) ORDER BY r.id ASC")
	List<Purchase> findPurchasedbyUser(User user);

}
