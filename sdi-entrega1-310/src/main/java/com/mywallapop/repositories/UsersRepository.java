package com.mywallapop.repositories;


import org.springframework.data.repository.CrudRepository;

import com.mywallapop.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
	
}
