package com.sasayaki7.studentroster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sasayaki7.studentroster.models.Contact;
import com.sasayaki7.studentroster.models.Dorm;
import com.sasayaki7.studentroster.models.Student;
import com.sasayaki7.studentroster.repositories.ContactRepository;
import com.sasayaki7.studentroster.repositories.DormRepository;
import com.sasayaki7.studentroster.repositories.StudentRepository;

@Service
public class ApiService {
	private final ContactRepository contactRepo;
	private final StudentRepository studentRepo;
	private final DormRepository dormRepo;
	public ApiService(ContactRepository contactRepo, StudentRepository studentRepo, DormRepository dormRepo) {
		this.dormRepo=dormRepo;
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
	
	public Student removeDorm(Student b) {
		b.setDorm(null);
		this.updateStudent(b);
		return b;
	}
	
	public Student addDorm(Student b, Dorm d) {
		b.setDorm(d);
		this.updateStudent(b);
		return b;
	}
	
	public Dorm createDorm(Dorm d) {
		return dormRepo.save(d);
	}
	
	public Dorm updateDorm(Dorm d) {
		return dormRepo.save(d);
	}
	
	public Dorm findDorm(Long id) {
		Optional<Dorm> optionalDorm = dormRepo.findById(id);
		if(optionalDorm.isPresent()) {
			return optionalDorm.get();
		}
		else {
			return null;
		}
	}
	
	public void deleteDorm(Long id) {
		dormRepo.deleteById(id);
	}
	
	public List<Dorm> getAllDorms(){
		return dormRepo.findAll();
	}
	
	public Contact updateContact(Contact b) {
		return contactRepo.save(b);
	}
	
	
	public void deleteContact(Long id) {
		contactRepo.deleteById(id);
	}
}
