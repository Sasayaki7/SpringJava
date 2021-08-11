package com.sasayaki7.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sasayaki7.studentroster.models.Contact;
import com.sasayaki7.studentroster.models.Student;
import com.sasayaki7.studentroster.repositories.ContactRepository;
import com.sasayaki7.studentroster.repositories.StudentRepository;

@Service
public class ApiService {
	private final ContactRepository contactRepo;
	private final StudentRepository studentRepo;
	public ApiService(ContactRepository contactRepo, StudentRepository studentRepo) {
		this.contactRepo = contactRepo;
		this.studentRepo = studentRepo;
	}
	
	public List<Student> allStudents(){
		return studentRepo.findAll();
	}
	
	public Student createStudent(Student b) {
		return studentRepo.save(b);
	}
	
	public Student findStudent(Long id) {
		Optional<Student> optionalStudent = studentRepo.findById(id);
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		}
		else {
			return null;
		}
	}
	
	public Student updateStudent(Student b) {
		return studentRepo.save(b);
	}
	
	
	public void deleteStudent(Long id) {
		studentRepo.deleteById(id);
	}

	
	public List<Contact> allContacts(){
		return contactRepo.findAll();
	}
	
	public Contact createContact(Contact b) {
		return contactRepo.save(b);
	}
	
	public Contact findContact(Long id) {
		Optional<Contact> optionalContact = contactRepo.findById(id);
		if(optionalContact.isPresent()) {
			return optionalContact.get();
		}
		else {
			return null;
		}
	}
	
	public Contact updateContact(Contact b) {
		return contactRepo.save(b);
	}
	
	
	public void deleteContact(Long id) {
		contactRepo.deleteById(id);
	}
}
