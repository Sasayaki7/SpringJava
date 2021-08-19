package com.sasayaki7.admindashboard.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.admindashboard.models.Role;
import com.sasayaki7.admindashboard.models.User;
import com.sasayaki7.admindashboard.services.UserService;
import com.sasayaki7.admindashboard.validators.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	 
	
	//----------------------AUTHENTICATION---------------------------------------------
	 
	@GetMapping("/registration")
	public String registerForm(@ModelAttribute("user") User user) {
	    return "loginRegistration.jsp";
	}
	 
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
	    userValidator.validate(user, result);
		if (result.hasErrors()) {
	        return "loginRegistration.jsp";
	    }
		user.setLastLogin(new Date());
		if(userService.allUsers().size() == 0) {
			userService.saveUserWithSuperAdminRole(user);
		}
		else if(userService.getAdminCount() == 0){
			userService.saveUserWithAdminRole(user);
		}
		else {
			userService.saveUserWithRole(user);
		}
	    return "redirect:/dashboard";
	}
	 
	
	@GetMapping("/login")
	public String login(@ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		if(error!=null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout successful");
		}
		
	    return "loginRegistration.jsp";
	}
	
	@GetMapping(value="/")
	public String redirect(Principal principal) {
		String username=principal.getName();
		User u =  userService.findByUsername(username);
		u.setLastLogin(new Date());
		return "redirect:/dashboard";
	}
	
	
	//-------------------------DASHBOARD--------------------------------------
	@GetMapping(value= "/dashboard")
	public String home(Principal principal, Model model) {
		
		String username=principal.getName();
		model.addAttribute("currentUser", userService.findByUsername(username));
		return "dashboard.jsp";
	}
	
	@GetMapping(value="/admin/dashboard")
	public String adminDashboard(Principal principal, Model model) {
		model.addAttribute("users", userService.allUsers());
		return "adminDashboard.jsp";
	}
	
	@GetMapping(value="/sysadmin/dashboard")
	public String sysadminDashboard(Principal principal, Model model) {
		model.addAttribute("users", userService.allUsers());
		return "sysadminDashboard.jsp";
	}
	
	
	//---------------------DELETE USERS-------------------------------------
	@DeleteMapping(value="/delete/admin/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		User u = userService.findUser(id);
		if(u != null) {
			if(! u.isAdmin() && ! u.isSuperAdmin()) {
				userService.deleteUser(id);
			}
		}
		return "redirect:/admin/dashboard";
	}
	
	@DeleteMapping(value="/delete/sysadmin/{id}")
	public String deleteSysUser(@PathVariable("id") Long id) {
		User u = userService.findUser(id);
		if(u != null) {
			if(! u.isSuperAdmin()) {
				userService.deleteUser(id);
			}
		}
		return "redirect:/sysadmin/dashboard";
	}
	
	
	//----------------------GIVE/LOSE ADMIN TO USERS-----------------------------
	@PutMapping(value="/admin/{id}")
	public String toAdmin(@PathVariable("id") Long id) {
		User u = userService.findUser(id);
		Role r = userService.findRole("ROLE_ADMIN").get(0);
		if (!u.getRoles().contains(r)) {
			u.getRoles().add(r);
			userService.updateUser(u);
		}
		return "redirect:/admin/dashboard";
	}
	
	
	@PutMapping(value="/sysadmin/unadmin/{id}")
	public String unAdmin(@PathVariable("id") Long id) {
		User u = userService.findUser(id);
		Role r = userService.findRole("ROLE_ADMIN").get(0);
		u.getRoles().remove(r);
		userService.updateUser(u);
		return "redirect:/sysadmin/dashboard";
	}
}
