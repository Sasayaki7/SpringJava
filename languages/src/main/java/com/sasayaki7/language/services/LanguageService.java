package com.sasayaki7.language.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sasayaki7.language.models.Language;
import com.sasayaki7.language.repositories.LanguageRepository;

@Service
public class LanguageService {
	private final LanguageRepository langRep;
	public LanguageService(LanguageRepository langRep) {
		this.langRep = langRep;
	}
	
	public List<Language> findAll(){
		return langRep.findAll();
	}
	
	public Language create(Language lang) {
		return langRep.save(lang);
	}

	public Language findLang(Long id) {
		Optional<Language> lang= langRep.findById(id);
		if (lang.isPresent()) {
			return lang.get();
		}
		else {
			return null;
		}
	}
	
	public Language updateLanguage(Language lang) {
		return langRep.save(lang);
	}
	
	public void deleteLang(Long id) {
		langRep.deleteById(id);
	}

}
