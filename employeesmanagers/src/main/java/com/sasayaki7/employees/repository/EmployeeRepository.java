package com.sasayaki7.employees.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.employees.models.Employee;



public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findAll();
}
