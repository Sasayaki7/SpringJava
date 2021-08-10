package com.sasayaki7.relationships.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.relationships.models.License;
import com.sasayaki7.relationships.models.Person;
import com.sasayaki7.relationships.services.LicenseService;
import com.sasayaki7.relationships.services.PersonService;

@Controller
public class MainController {
	private final LicenseService liceService;
	private final PersonService personService;
	
	public MainController(LicenseService liceService, PersonService personService) {
		this.liceService = liceService;
		this.personService = personService;
	}
	
	@RequestMapping("/persons/new")
	public String personForm(Model model) {
		model.addAttribute("person", new Person());
		return "newperson.jsp";
	}
	
	@RequestMapping(value="/persons/new", method=RequestMethod.POST)
	public String createPerson(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		if (result.hasErrors()) {
			return "newperson.jsp";
		}
		else {
			personService.createPerson(person);
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value="/licenses/new", method=RequestMethod.POST)
	public String createLicense(@RequestParam(value="expirationDate") String date, @RequestParam(value="state") String state, @RequestParam(value="personId") Long personId) throws ParseException {
		Person person = personService.findPerson(personId);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		System.out.println(date);
		String number = String.format("%06d", liceService.allLicenses().size());
		Date newDate = formatter.parse(date);
		License newLicense = new License(state, newDate, number, person);
		liceService.createLicense(newLicense);
		return "redirect:/";
	}
	
	@RequestMapping("/licenses/new")
	public String licenseForm(Model model) {
		List<Person> people = personService.allPersons();
		model.addAttribute("license", new License());
		model.addAttribute("people", people);
		return "newlicense.jsp";
	}
	
	@RequestMapping("/persons/{id}")
	public String showPerson(@PathVariable("id") Long id, Model model) {
		Person person = personService.findPerson(id);
		model.addAttribute("person", person);
		return "showperson.jsp";
	}
}
