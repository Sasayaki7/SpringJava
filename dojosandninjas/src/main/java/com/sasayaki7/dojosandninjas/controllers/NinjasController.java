package com.sasayaki7.dojosandninjas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.sasayaki7.dojosandninjas.services.NinjaService;

@Controller
public class NinjasController {

	@Autowired
	private ApiServices apiServ;
	@Autowired
	private NinjaService ninjaServ;
	
	
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
		return "redirect:/dojos/new";
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
		return "redirect:/ninjas/new";
	}
	
	@RequestMapping("/dojos/{id}")
	public String showAllFromDojo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dojos", apiServ.getDojo(id));
		return "dashboard.jsp";
	}
	
	@RequestMapping("/pages/{pageNumber}")
	public String getNinjasPerPage(Model model, @PathVariable("pageNumber") int pageNumber) {
	    // we have to subtract 1 because the Pages iterable is 0 indexed. This is for our links to be able to show from 1...pageMax, instead of 0...pageMax class="keyword operator from-rainbow">- 1.
	    Page<Ninja> ninjas = ninjaServ.ninjasPerPage(pageNumber - 1);
	    // total number of pages that we have
	    int totalPages = ninjas.getTotalPages();
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("ninjas", ninjas);
	    return "ninjas.jsp";
	}

	@RequestMapping("/dojos/pages/{pageNumber}")
	public String getDojosAndNinjasPerPage(Model model, @PathVariable("pageNumber") int pageNumber) {
	    // we have to subtract 1 because the Pages iterable is 0 indexed. This is for our links to be able to show from 1...pageMax, instead of 0...pageMax class="keyword operator from-rainbow">- 1.
	    Page<Object[]> dojoNinjas = ninjaServ.dojosAndNinjasPerPage(pageNumber-1);
	    // total number of pages that we have
	    int totalPages = dojoNinjas.getTotalPages();
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("dojoninjas", dojoNinjas);
	    return "ninjas2.jsp";
	}

}
