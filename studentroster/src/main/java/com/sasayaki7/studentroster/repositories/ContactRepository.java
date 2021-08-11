package com.sasayaki7.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.studentroster.models.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long>{
	List<Contact> findAll();
}
