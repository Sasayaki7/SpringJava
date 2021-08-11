package com.sasayaki7.dojosandninjas.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.dojosandninjas.models.Dojo;
import com.sasayaki7.dojosandninjas.models.Ninja;
import com.sasayaki7.dojosandninjas.services.ApiServices;

@Controller
public class NinjasController {
	private ApiServices apiServ;
	public NinjasController(ApiServices apiServ) {
		this.apiServ = apiServ;
	}
	
	@RequestMapping("/dojos/new")
	public String dojoForm(Model model) {
		model.addAttribute("dojo", new Dojo());
		return "dojoform.jsp";
	}
	
	@RequestMapping(value="/dojos/new", method=RequestMethod.POST)
	public String submitDojo(@Valid @ModelAttribute(value="dojo") Dojo dojo, BindingResult result) {
		if (result.hasErrors()) {
			return "dojoform.jsp";
		}
		apiServ.createDojo(dojo);
		return "redirect:/";
	}
	
	@RequestMapping("/ninjas/new")
	public String ninjaForm(Model model) {
		model.addAttribute("dojos", apiServ.findAllDojos());
		return "ninjaform.jsp";
	}
	
	@RequestMapping(value="/ninjas/new", method=RequestMethod.POST)
	public String submitDojo(@RequestParam(value="dojoId") Long id, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="age") int age) {
		Ninja newNinja = new Ninja(firstName, lastName, age);
		newNinja.setDojo(apiServ.getDojo(id));
		apiServ.createNinja(newNinja);
		return "redirect:/";
	}
	
	@RequestMapping("/dojos/{id}")
	public String showAllFromDojo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dojos", apiServ.getDojo(id));
		return "dashboard.jsp";
	}
}
