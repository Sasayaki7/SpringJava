package com.sasayaki7.authentication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.authentication.models.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findAll();
	List<Event> findByState(String state);
	List<Event> findByStateNot(String state);
}
