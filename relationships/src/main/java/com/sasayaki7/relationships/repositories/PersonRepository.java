package com.sasayaki7.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.relationships.models.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
	public List<Person> findAll();
}
