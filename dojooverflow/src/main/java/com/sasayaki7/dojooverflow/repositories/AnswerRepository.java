package com.sasayaki7.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.dojooverflow.models.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	List<Answer> findAll();
}
