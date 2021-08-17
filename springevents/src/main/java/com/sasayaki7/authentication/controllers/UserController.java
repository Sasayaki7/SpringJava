package com.sasayaki7.authentication.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sasayaki7.authentication.models.Event;
import com.sasayaki7.authentication.models.User;
import com.sasayaki7.authentication.services.ApiService;
import com.sasayaki7.authentication.validators.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	private ApiService apiServ;
	@Autowired
	private UserValidator userValidator;
	
	private final String[] states = {
			"AL", "AK", "AR", "AS", "AZ", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV",
				"NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "VI", "WA", "WV", "WI", "WY"
	};
	
	//-----------------REGISTRATION-----------------
	@GetMapping("/")
	public String getRegistration(@ModelAttribute User user, Model model) {
		model.addAttribute("state", states);
		return "loginAndRegistration.jsp";
	}

	
	@PostMapping("/")
	public String registerUser(@Valid @ModelAttribute User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "loginAndRegistration.jsp";
		}
		else {
			
			User u = apiServ.registerUser(user);
			session.setAttribute("uuid", u.getId());
			return "redirect:/events";
		}
	}
	
	
	//------------------------LOGIN-----------------------
	
	@PostMapping("/login")
	public String loginUser(String email, String password, RedirectAttributes redirect, HttpSession session) {
		if(apiServ.authenticateUser(email, password)) {
			session.setAttribute("uuid", apiServ.findUserByEmail(email).getId());
			return "redirect:/events";
		}
		else {
			redirect.addFlashAttribute("error", "Invalid Credentials");
			return "redirect:/";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	

}
