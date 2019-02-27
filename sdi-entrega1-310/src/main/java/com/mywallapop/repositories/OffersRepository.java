package com.mywallapop.repositories;

import org.springframework.data.repository.CrudRepository;
import com.mywallapop.entities.Offer;

public interface OffersRepository extends CrudRepository<Offer, Long> {

}
