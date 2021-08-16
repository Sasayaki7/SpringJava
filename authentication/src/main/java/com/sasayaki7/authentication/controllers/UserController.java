package com.sasayaki7.authentication.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.authentication.models.User;
import com.sasayaki7.authentication.services.UserService;
import com.sasayaki7.authentication.validator.UserValidator;

// imports removed for brevity

@Controller
public class UserController {
	
	@Autowired
    private UserService userService;
	@Autowired
	private UserValidator userValidator;
    

    
    @GetMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    @GetMapping("/login")
    public String login() {
        return "loginPage.jsp";
    }
    
    @PostMapping(value="/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
    	if (result.hasErrors()) {
    		return "registrationPage.jsp";
    	}
    	else {
    		User u = userService.registerUser(user);
    		session.setAttribute("uuid", u.getId());
    		return "redirect:/home";
    	}
        // if result has errors, return the registration page (don't worry about validations just now)
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    }
    
    @PostMapping(value="/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
    	if (userService.authenticateUser(email, password)){
    		session.setAttribute("uuid", userService.findByEmail(email).getId());
    		return "redirect:/home";
    	}
    	else {
    		model.addAttribute("error", "Invalid credentials");
    		return "loginPage.jsp";
    	}
        // if the user is authenticated, save their user id in session
        // else, add error messages and return the login page
    }
    
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
    	model.addAttribute("user", userService.findUserById((Long) session.getAttribute("uuid")));
    	return "homePage.jsp";
        // get user from session, save them in the model and return the home page
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        // redirect to login page
    	session.invalidate();
    	return "redirect:/login";
    }
}
