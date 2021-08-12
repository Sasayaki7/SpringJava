package com.sasayaki7.studentroster.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.studentroster.models.Contact;
import com.sasayaki7.studentroster.models.Dorm;
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
	
	@RequestMapping("/dorms/new")
	public String newDorm() {
		return "dormform.jsp";
	}
	
	@RequestMapping("/dorms/{id}/remove")
	public String removeDormFromStudent(@PathVariable("id") Long id, @RequestParam(value="student") Long studentId) {
		Student s = api.findStudent(studentId);
		api.removeDorm(s);
		return "redirect:/dorms/"+id;
	}
	
	
	@RequestMapping("/dorms/create")
	public String createDorm(@RequestParam(value="name") String name) {
		Dorm d = new Dorm(name);
		Dorm newD = api.createDorm(d);
		return "redirect:/dorms/"+newD.getId();
	}
	
	@RequestMapping("/dorms/{id}")
	public String seeDormInfo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("studentsWithoutDorms", api.allStudents());
		model.addAttribute("dorm", api.findDorm(id));
		return "dormdashboard.jsp";
	}
	
	@RequestMapping("/dorms/{id}/add")
	public String addStudentToDorm(@PathVariable("id") Long id, @RequestParam(value="student") Long studentId) {
		Student s = api.findStudent(studentId);
		api.addDorm(s, api.findDorm(id));
		return "redirect:/dorms/"+id;
	}
}
