package com.sasayaki7.ninjagold;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.Date;

import javax.servlet.http.HttpSession;

@Controller
public class GoldController {
	
	private Random randMachine = new Random();
	private Locations[] location = {new Locations("Farm", 10, 20), new Locations("Cave", 5, 10),new Locations("House", 2, 5),
			new Locations("Casino", -50, 50), new Locations("Spa", -20, -5)};
	
	@RequestMapping("/")
	public String mainPage(HttpSession session, Model model, @RequestParam(value="reset", required=false) String reset) {
		if (reset != null && reset.equals("true")) {
			session.removeAttribute("gold");
			session.removeAttribute("logs");
		}
		if (session.getAttribute("gold") == null) {
			session.setAttribute("gold", 0);
		}
		if (session.getAttribute("logs") == null) {
			session.setAttribute("logs", new ArrayList<String>());
		}
		
		if ((Integer) session.getAttribute("gold") < -100) {
			return "redirect:/jail";
		}
		model.addAttribute("locationArray", location);
		return "ninjahome.jsp";
	}
	
	private String processString(String place, int gain) {
		Date now = new Date();
		String str = "" + now + ": You entered "+place + " and ";
		if (gain < 0) {
			str += " lost " + Math.abs(gain) + " gold";
		}
		else {
			str+= " gained " + gain+ " gold";
		}
		return str;
	}

	
	private int getGold(String min, String max) {
		return randMachine.nextInt(Integer.parseInt(max)+1-Integer.parseInt(min))+Integer.parseInt(min);
	}
	
	@RequestMapping("/jail")
	public String jail() {
		return "debtjail.jsp";
	}
	
	@RequestMapping("/goldsubmission")
	public String formSubmit(@RequestParam(value="place") String place, @RequestParam(value="min") String min, 
			@RequestParam(value="max") String max, HttpSession session) {
		if (session.getAttribute("gold") == null) {
			session.setAttribute("gold", 0);
		}
		if (session.getAttribute("logs") == null) {
			session.setAttribute("logs", new ArrayList<String>());
		}
		
		int gold = getGold(min, max);
		//Get a random value.
		session.setAttribute("gold", (Integer) session.getAttribute("gold")+gold);
		
		//Get date for timestamp
		String str = processString(place, gold);
		
		ArrayList<String> arr = (ArrayList<String>) session.getAttribute("logs");
		arr.add(0, str);
		session.setAttribute("logs", arr);
		return "redirect:/";
	}
	
	
	
}
