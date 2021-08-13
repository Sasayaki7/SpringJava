package com.sasayaki7.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.employees.models.Employee;
import com.sasayaki7.employees.services.EmployeeService;

@Controller
public class MainController {
	
	@Autowired
	private EmployeeService empService;
	
	
	@PostMapping("/employee/new")
	public String newEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		Employee newEmp = new Employee(firstName, lastName);
		empService.createEmployee(newEmp);
		return "redirect:/employee/new";
	}
	
	@GetMapping("/employee/new")
	public String employeeForm() {
		return "employeeform.jsp";
	}
	
	
	@GetMapping("/employee/{id}/addmanager")
	public String addManager(@PathVariable("id") Long id, @RequestParam("managerId") Long managerId) {
		Employee emp = empService.findEmployee(id);
		Employee manager = empService.findEmployee(managerId);
		emp.setManager(manager);
		empService.updateEmployee(emp);
		return "redirect:/employee/new";
	}
	
	
	@GetMapping("/manager/{id}")
	public String showEmployees(@PathVariable("id") Long id, Model model) {
		Employee manager = empService.findEmployee(id);
		model.addAttribute("manager", manager);
		return "showemployees.jsp";
	}

}
