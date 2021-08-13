package com.sasayaki7.employees.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasayaki7.employees.models.Employee;
import com.sasayaki7.employees.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public Employee createEmployee(Employee q) {
		return empRepo.save(q);
	}
	
	public Employee updateEmployee(Employee q) {
		return empRepo.save(q);
	}
	
	public Employee findEmployee(Long id) {
		Optional<Employee> maybeQ = empRepo.findById(id);
		if (maybeQ.isPresent()) {
			return maybeQ.get();
		}
		else {
			return null;
		}
	}
	
	public List<Employee> allEmployees(){
		return empRepo.findAll();
	}
}
