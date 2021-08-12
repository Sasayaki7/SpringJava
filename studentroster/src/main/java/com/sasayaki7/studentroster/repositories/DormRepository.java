package com.sasayaki7.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.studentroster.models.Dorm;

public interface DormRepository extends CrudRepository<Dorm, Long>{
	List<Dorm> findAll();
}
