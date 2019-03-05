package com.mywallapop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mywallapop.entities.Conversation;
import com.mywallapop.entities.Message;
import com.mywallapop.entities.User;


public interface MessagesRepository extends CrudRepository<Message, Long> {

	@Query("SELECT r FROM Message r WHERE r.conversation = ?1  and (r.conversation.sender =?2 or r.conversation.offer.user =?2) ORDER BY r.id ASC ")
	List<Message> findAllByOrderByIdAsc(Conversation conversation, User user);
	
}
