package com.sasayaki7.world.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.world.models.City;
import com.sasayaki7.world.services.ApiService;

@Controller
public class MainControllers {
	
	@Autowired
	private ApiService apiService;
	
	
	@GetMapping("/")
	public String showQuery(@RequestParam(value="language", required=false) String lang,  @RequestParam(value="country", required=false) String country, @RequestParam(value="population", required=false) String population, Model model) {
//		List<Object[]> results = apiService.getLangPercentAndCountry(lang);
//		List<Object[]> results = apiService.getCityCountByCountry();
		int popToNum = Integer.parseInt(population);
		List<City> results = apiService.getCitysInCountryWherePopulation(country, popToNum);
		model.addAttribute("results", results);
		return "index3.jsp";
	}
}
