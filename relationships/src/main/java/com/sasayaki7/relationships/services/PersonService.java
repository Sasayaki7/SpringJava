package com.sasayaki7.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sasayaki7.relationships.models.Person;
import com.sasayaki7.relationships.repositories.PersonRepository;

@Service
public class PersonService {
	private final PersonRepository personRepo;
	public PersonService(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}
	
	public List<Person> allPersons(){
		return personRepo.findAll();
	}
	
	public Person createPerson(Person b) {
		return personRepo.save(b);
	}
	
	public Person findPerson(Long id) {
		Optional<Person> optionalPerson = personRepo.findById(id);
		if(optionalPerson.isPresent()) {
			return optionalPerson.get();
		}
		else {
			return null;
		}
	}
	
	public Person updatePerson(Person b) {
		return personRepo.save(b);
	}
	
	
	public void deletePerson(Long id) {
		personRepo.deleteById(id);
	}
}
