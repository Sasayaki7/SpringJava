package com.sasayaki7.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.dojooverflow.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	List<Question> findAll();
}
