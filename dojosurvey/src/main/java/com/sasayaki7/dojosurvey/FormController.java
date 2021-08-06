package com.sasayaki7.dojosurvey;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
	
	@RequestMapping("/")
	public String preForm() {
		return "formpage.jsp";
	}
	
	@RequestMapping("/result")
	public String formResult(Model model) {
		return "result.jsp";
	}
	
	@RequestMapping("/postform")
	public String formPost(@RequestParam(value="name") String name, @RequestParam(value="language") String language, 
			@RequestParam(value="location") String location, @RequestParam(value="comment", required=false) String comment, HttpSession session) {
		session.setAttribute("name", name);
		session.setAttribute("language", language);
		session.setAttribute("location", location);
		session.setAttribute("comment", comment);
		if (language.equals("Java")) {
			return "redirect:/java";
		}
		return "redirect:/result";
	}
	
	@RequestMapping("/java")
	public String toJava() {
		return "java.jsp";
	}
}
