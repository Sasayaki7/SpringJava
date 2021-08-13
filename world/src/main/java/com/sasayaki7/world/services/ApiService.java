package com.sasayaki7.world.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasayaki7.world.models.City;
import com.sasayaki7.world.repositories.CityRepository;
import com.sasayaki7.world.repositories.LanguageRepository;
import com.sasayaki7.world.repositories.WorldRepository;

@Service
public class ApiService {
	
	@Autowired
	private LanguageRepository langRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private WorldRepository worldRepo;
	
	
	public List<Object[]> getLangPercentAndCountry(String languageName) {
		return langRepo.getLanguagePercentageAndCountry(languageName);
	}
	
	public List<Object[]> getCityCountByCountry(){
		return worldRepo.getAllCityCount();
	}
	
	
	public List<City> getCitysInCountryWherePopulation(String country, int population){
		return worldRepo.getCitiesInCountry(country, population);
	}
}
