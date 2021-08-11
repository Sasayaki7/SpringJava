package com.sasayaki7.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.studentroster.models.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
	List<Student> findAll();
}
