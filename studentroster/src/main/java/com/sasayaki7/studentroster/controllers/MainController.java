package com.sasayaki7.studentroster.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.studentroster.models.Contact;
import com.sasayaki7.studentroster.models.Student;
import com.sasayaki7.studentroster.services.ApiService;

@Controller
public class MainController {
	private ApiService api;
	public MainController(ApiService api) {
		this.api = api;
	}
	
	
	@RequestMapping("/students/new")
	public String studentForm() {
		return "studentform.jsp";
	}
	
	@RequestMapping("/contact/new")
	public String contactForm(Model model) {
		List<Student> studentList = api.allStudents();
		model.addAttribute("students", studentList);
		return "contactform.jsp";
	}
	
	@RequestMapping("/students/create")
	public String createStudent(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="age") Integer age) {
		Student s = new Student(firstName, lastName, age);
		api.createStudent(s);
		return "redirect:/students";
	}
	
	@RequestMapping("/contacts/create")
	public String createContact(@RequestParam(value="studentId") Long studentId, @RequestParam(value="address") String address, @RequestParam(value="city") String city, @RequestParam(value="state") String state) {
		Contact c = new Contact(address, city, state);
		c.setStudent(api.findStudent(studentId));
		api.createContact(c);
		return "redirect:/students";
	}
	
	@RequestMapping("/students")
	public String showStudents(Model model) {
		List<Student> students = api.allStudents();
		model.addAttribute("students", students);
		return "students.jsp";
	}
}
