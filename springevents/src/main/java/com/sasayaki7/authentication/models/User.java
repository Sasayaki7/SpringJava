package com.sasayaki7.authentication.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name="users")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank(message="First Name must not be empty")
	@Size(min=2, message="First name must be at least 2 characters")
	private String firstName;
	
	@NotBlank(message="Last Name must not be empty")
	@Size(min=2, message="Last Name must be at least 2 characters")
	private String lastName;
	
	@NotBlank(message="Email must not be empty")
	@Email(message="Email must be in a valid format")
	private String email;
	
	@NotBlank(message="The city field must be filled")
	private String city;
	
	@NotBlank(message="The state field must be filled")
	private String state;
	
	@NotBlank(message="The password must not be empty")
	@Size(min=5, message="The password must be at least 5 characters")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;

	
	@OneToMany(mappedBy="host", fetch=FetchType.LAZY)
	private List<Event> hostedEvents;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="event_attendee",
		joinColumns=@JoinColumn(name="attendee_id"),
		inverseJoinColumns=@JoinColumn(name="event_id"))
	private List<Event> eventsAttending;
	
	
	@OneToMany(mappedBy="poster", fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	public User() {
	}
		
		
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public List<Event> getHostedEvents() {
		return hostedEvents;
	}


	public void setHostedEvents(List<Event> hostedEvents) {
		this.hostedEvents = hostedEvents;
	}


	public List<Event> getEventsAttending() {
		return eventsAttending;
	}


	public void setEventsAttending(List<Event> eventsAttending) {
		this.eventsAttending = eventsAttending;
	}


	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	
}
