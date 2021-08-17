package com.sasayaki7.authentication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasayaki7.authentication.models.Comment;
import com.sasayaki7.authentication.models.Event;
import com.sasayaki7.authentication.models.User;
import com.sasayaki7.authentication.repositories.CommentRepository;
import com.sasayaki7.authentication.repositories.EventRepository;
import com.sasayaki7.authentication.repositories.UserRepository;

@Service
public class ApiService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private CommentRepository commRepo;
	
	//------------USER REGISTRATION/AUTH-------------------------
	public User registerUser(User u) {
		String hashPassword = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
		u.setPassword(hashPassword);
		return userRepo.save(u);
	}
	
	public User findUserById(Long id) {
		Optional<User> tempUser = userRepo.findById(id);
		if (tempUser.isPresent()) {
			return tempUser.get();
		}
		else {
			return null;
		}
	}
	
	public User findUserByEmail(String email) {
		Optional<User> tempUser = userRepo.findByEmail(email);
		if (tempUser.isPresent()) {
			return tempUser.get();
		}
		else {
			return null;
		}
	}
	
	public boolean authenticateUser(String email, String password) {
		User u = this.findUserByEmail(email);
		if (u == null) {
			return false;
		}
		else {
			if (BCrypt.checkpw(password, u.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	
	
	//-------------------EVENTS---------------------------
	public List<Event> allEvents(){
		return eventRepo.findAll();
	}
	
	public List<Event> allEventInState(String state){
		return eventRepo.findByState(state);
	}
	
	public List<Event> allEventNotInState(String state){
		return eventRepo.findByStateNot(state);
	}
	
	public Event createEvent(Event e) {
		return eventRepo.save(e);
	}
	
	public Event updateEvent(Event e) {
		return eventRepo.save(e);
	}
	
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}
	
	public Event findEvent(Long id) {
		Optional<Event> tempEvent = eventRepo.findById(id);
		if (tempEvent.isPresent()) {
			return tempEvent.get();
		}
		else {
			return null;
		}
	}
	
	
	//------------------EVENTS RELATIONSHIPS------------------------
	public Event addAttendeeToEvent(Long eventId, Long attendeeId) {
		Event e = this.findEvent(eventId);
		if (e != null) {
			e.getAttendees().add(this.findUserById(attendeeId));
			return this.updateEvent(e);
		}
		else {
			return null;
		}
	}
	
	public Event removeAttendeeFromEvent(Long eventId, Long attendeeId) {
		Event e = this.findEvent(eventId);
		if (e != null) {
			e.getAttendees().remove(this.findUserById(attendeeId));
			return this.updateEvent(e);
		}
		else {
			return null;
		}
	}
	
	
	public List<Object[]> inStateEventCheckAttendance(String state, User u) {
		List<Event> inStateEvents = this.allEventInState(state);
		List<Object[]> eventWithAttendance = new ArrayList<Object[]> ();
		for (Event e: inStateEvents) {
			Object[] arr = new Object[2];
			arr[0] = e;
			arr[1] = (Boolean) e.getAttendees().contains(u);
			eventWithAttendance.add(arr);
		}
		return eventWithAttendance;
	}
	
	
	public List<Object[]> outOfStateEventCheckAttendance(String state, User u) {
		List<Event> outOfStateEvents = this.allEventNotInState(state);
		List<Object[]> eventWithAttendance = new ArrayList<Object[]> ();
		for (Event e: outOfStateEvents) {
			Object[] arr = new Object[2];
			arr[0] = e;
			arr[1] = (Boolean) e.getAttendees().contains(u);
			eventWithAttendance.add(arr);
		}
		return eventWithAttendance;
	}
	
	//----------------------COMMENTS RELATIONSHIPS---------------------------------
	public List<Comment> allComments(){
		return commRepo.findAll();
	}
	
	public Comment createComment(Comment c) {
		return commRepo.save(c);
	}
	
	public Comment createComment(Comment c, Long userId, Long eventId) {
		Comment cc = new Comment();
		cc.setComment(c.getComment());
		User u = this.findUserById(userId);
		cc.setPoster(u);
		cc.setEvent(this.findEvent(eventId));
		return commRepo.save(cc);
	}
}
