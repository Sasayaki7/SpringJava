package com.sasayaki7.dojooverflow.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.dojooverflow.models.Tags;

public interface TagsRepository extends CrudRepository<Tags, Long> {
	List<Tags> findAll();
	boolean existsBySubject(String text);
	Optional<Tags> findBySubject(String text);
}
