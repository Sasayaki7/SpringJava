package com.sasayaki7.authentication.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.sasayaki7.authentication.models.Comment;
import com.sasayaki7.authentication.models.Event;
import com.sasayaki7.authentication.models.User;
import com.sasayaki7.authentication.services.ApiService;


@Controller
public class EventController {
	
	@Autowired
	private ApiService apiServ;

	
	
	//------------------------SETUP------------------------------------
	private final String[] states = {
			"AL", "AK", "AR", "AS", "AZ", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV",
				"NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "VI", "WA", "WV", "WI", "WY"
	};
	
	
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
    
    
	
	//-------------CREATE EVENTS-------------------
	@GetMapping("/events")
	public String showEventDashboard(@ModelAttribute("event") Event event, Model model, HttpSession session) {
		if (session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		model.addAttribute("state", states);
		User u = apiServ.findUserById((Long) session.getAttribute("uuid"));
		String state = u.getState();
		model.addAttribute("eventsInState", apiServ.inStateEventCheckAttendance(state, u));
		model.addAttribute("eventsOutOfState", apiServ.outOfStateEventCheckAttendance(state, u));

		return "eventdashboard.jsp";
	}
	
	
	@PostMapping("/events")
	public String addEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("state", states);
			String state = apiServ.findUserById((Long) session.getAttribute("uuid")).getState();
			model.addAttribute("eventsInState", apiServ.allEventInState(state));
			model.addAttribute("eventsOutOfState", apiServ.allEventNotInState(state));
			return "eventdashboard.jsp";
		}
		else {
			User host = apiServ.findUserById((Long) session.getAttribute("uuid"));
			event.setHost(host);
			apiServ.createEvent(event);
			return "redirect:/events";
		}
	}
	
	
	//-------------JOIN AND CANCEL EVENTS------------------------
	@GetMapping("/events/{id}/join")
	public String joinEvent(@PathVariable("id") Long eventId, HttpSession session) {
		apiServ.addAttendeeToEvent(eventId, (Long) session.getAttribute("uuid"));
		return "redirect:/events";
	}

	@GetMapping("/events/{id}/cancel")
	public String cancelEvent(@PathVariable("id") Long eventId, HttpSession session) {
		apiServ.removeAttendeeFromEvent(eventId, (Long) session.getAttribute("uuid"));
		return "redirect:/events";
	}
	

    
    //------------------EDIT EVENTS------------------------------
	@GetMapping("/events/{id}/edit")
	public String editEventForm(@PathVariable("id") Long eventId, Model model, HttpSession session) {
		Event e = apiServ.findEvent(eventId);
		if((e.getHost().getId()).longValue() != ((Long) session.getAttribute("uuid")).longValue()) {

			return "redirect:/events";
		}
		model.addAttribute("event", e);
		model.addAttribute("state", states);
		return "eventEditForm.jsp";
	}
	
	@PutMapping("/events/{id}/edit")
	public String editEvent(@PathVariable("id") Long eventId, @Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			return "eventEditForm.jsp";
		}
		else {
			event.setHost(apiServ.findUserById((Long) session.getAttribute("uuid")));
			apiServ.updateEvent(event);
			return "redirect:/events";
		}
	}
	
	
	//----------------------DELETE EVENTS-----------------------
	@DeleteMapping("/events/{id}")
	public String editEventForm(@PathVariable("id") Long eventId) {
		apiServ.deleteEvent(eventId);
		return "redirect:/events";
	}
	
	
	//--------------------SHOW EVENTS------------------------------
	@GetMapping("/events/{id}")
	public String showEvent(@PathVariable("id") Long eventId, @ModelAttribute("comm") Comment comment, Model model) {
		model.addAttribute("event", apiServ.findEvent(eventId));
		return "showEvent.jsp";
	}
	
	
	//--------------------ADD COMMENT-------------------------------
	@PostMapping("/events/{id}/addComment")
	public String addComment(@PathVariable("id") Long eventId, @ModelAttribute("comm") Comment comment, BindingResult result, HttpSession session, Model model) {
		System.out.println(comment.getComment());

		if(result.hasErrors()) {
			model.addAttribute("event", apiServ.findEvent(eventId));
			return "showEvent.jsp";
		}
		apiServ.createComment(comment, (Long) session.getAttribute("uuid"), eventId);
		return "redirect:/events/"+eventId;
	}
}
