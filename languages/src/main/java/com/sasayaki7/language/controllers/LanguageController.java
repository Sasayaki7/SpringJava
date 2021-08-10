package com.sasayaki7.language.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sasayaki7.language.models.Language;
import com.sasayaki7.language.services.LanguageService;

@Controller
public class LanguageController {
	private final LanguageService langServ;
	
	
	public LanguageController(LanguageService langServ) {
		this.langServ = langServ;
	}
	
	
	@RequestMapping("/languages")
	public String showLangs(Model model) {
		List<Language> langs = langServ.findAll();
		model.addAttribute("languages", langs);
		model.addAttribute("language", new Language());
		return "languages.jsp";
	}

	@RequestMapping(value="/languages", method=RequestMethod.POST)
	public String postLang(@Valid @ModelAttribute("language") Language language, BindingResult result)
	{
		if (result.hasErrors()) {
			return "languages.jsp";
		}
		else {
			langServ.create(language);
			return "redirect:/languages";
		}
	}
	
	@RequestMapping("/languages/{id}/edit")
	public String editLangs(@PathVariable("id") Long id, Model model) {
		Language lang = langServ.findLang(id);
		model.addAttribute("language", lang);
		return "editlang.jsp";
	}
	
	
	@RequestMapping(value="/languages/{id}", method=RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("language") Language lang, BindingResult result, @PathVariable("id") Long id) {

		if (result.hasErrors()) {
			return "editlang.jsp";
		}
		else {
			langServ.updateLanguage(lang);
			return "redirect:/languages";
		}
	}

	@RequestMapping(value="/languages/{id}", method=RequestMethod.GET)
	public String showLang(@PathVariable("id") Long id, Model model) {
		Language lang = langServ.findLang(id);
		if (lang == null) {
			return "redirect:/languages";
		}
		else {
			model.addAttribute("language", lang);
			return "show.jsp";
		}
	}
	
	@RequestMapping(value="/languages/{id}", method=RequestMethod.DELETE)
	public String deleteLang(@PathVariable("id") Long id) {
		langServ.deleteLang(id);
		return "redirect:/languages";
	}
}
