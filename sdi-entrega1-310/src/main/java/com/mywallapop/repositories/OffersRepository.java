package com.mywallapop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.mywallapop.entities.Offer;

public interface OffersRepository extends CrudRepository<Offer, Long> {

	Page<Offer> findAll(Pageable pageable);
	
	@Query("SELECT r FROM Offer r WHERE (LOWER(r.title) LIKE LOWER(?1) )")
	Page<Offer> searchByTitle(Pageable pageable, String seachtext);

}
