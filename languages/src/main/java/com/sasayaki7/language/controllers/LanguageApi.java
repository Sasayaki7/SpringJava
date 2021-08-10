package com.sasayaki7.language.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sasayaki7.language.models.Language;
import com.sasayaki7.language.services.LanguageService;


@RestController
public class LanguageApi {
	private final LanguageService langServ;
	public LanguageApi(LanguageService langServ) {
		this.langServ = langServ;
	}
	
	
	@RequestMapping("/api/languages")
	public List<Language> findAll(){
		return langServ.findAll();
	}
	
	@RequestMapping(value="/api/new", method=RequestMethod.POST)
	public Language createLang(@RequestParam(value="name") String name, @RequestParam(value="creator") String creator, @RequestParam(value="version") String version) {
		Language lang = new Language(name, creator, version);
		return langServ.create(lang);
	}
	
	@RequestMapping(value="/api/languages/{id}", method=RequestMethod.PUT)
	public Language updateLang(@PathVariable("id") Long id, @RequestParam(value="name") String name, @RequestParam(value="creator") String creator, @RequestParam(value="version") String version) {		
		Language lang = langServ.findLang(id);
		lang.setCreator(creator);
		lang.setName(name);
		lang.setVersion(version);
		lang.setId(id);
		return langServ.updateLanguage(lang);
	}
	
	@RequestMapping(value="/api/langauges/{id}")
	public Language showLang(@PathVariable("id") Long id) {
		return langServ.findLang(id);
	}
	
	@RequestMapping(value="/api/languages/{id}", method=RequestMethod.DELETE)
	public void deleteLang(@PathVariable("id") Long id) {
		langServ.deleteLang(id);
	}
}
