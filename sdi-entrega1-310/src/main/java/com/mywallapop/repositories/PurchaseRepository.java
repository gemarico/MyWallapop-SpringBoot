package com.mywallapop.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mywallapop.entities.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

}
