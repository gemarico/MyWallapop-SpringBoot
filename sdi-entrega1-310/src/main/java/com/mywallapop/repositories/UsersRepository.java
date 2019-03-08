package com.mywallapop.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mywallapop.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	
	@Query("SELECT u FROM User u WHERE (u <> ?1) ORDER BY u.id ASC")
	List<User> findUsers( User user);
	
	User findByEmail(String email);
	
	
}
