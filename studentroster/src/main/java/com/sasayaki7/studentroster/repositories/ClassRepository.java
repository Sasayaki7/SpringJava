package com.sasayaki7.studentroster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.sasayaki7.studentroster.models.Classes;
import com.sasayaki7.studentroster.models.Student;

public interface ClassRepository extends CrudRepository<Classes, Long>{
	List<Classes> findAll();
    List<Classes> findByStudentsNotContains(Student s);
}
