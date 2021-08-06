package com.sasayaki7.counter;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CounterController {
	@RequestMapping("/")
	public String tomain(HttpSession session) {
		if(session.getAttribute("counter")==null) {
			session.setAttribute("counter", 1);
		}
		else {
			session.setAttribute("counter", (Integer) session.getAttribute("counter") +1);

		}
		return "main.jsp";
	}
	
	@RequestMapping("/counter")
	public String counterDisplay(HttpSession session, Model model, @RequestParam(value="reset", required=false) String reset) {
		if ( reset != null && reset.equals("true")) {
			System.out.println("hi");
			session.removeAttribute("counter");
		}
		if(session.getAttribute("counter")==null) {
			session.setAttribute("counter", 0);
		}
		model.addAttribute("count", (Integer) session.getAttribute("counter"));
		return "counttrack.jsp";
	}
	
	@RequestMapping("/counter2x")
	public String doubleCounter(HttpSession session) {
		if(session.getAttribute("counter")==null) {
			session.setAttribute("counter", 2);
		}
		else {
			session.setAttribute("counter", (Integer) session.getAttribute("counter") +2);
		}
		return "counttrackdouble.jsp";
	}
}
