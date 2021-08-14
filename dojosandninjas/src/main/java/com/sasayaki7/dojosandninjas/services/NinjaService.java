package com.sasayaki7.dojosandninjas.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sasayaki7.dojosandninjas.models.Ninja;
import com.sasayaki7.dojosandninjas.repositories.NinjaRepository;

@Service
@Transactional
public class NinjaService {
	
	@Autowired
	private NinjaRepository ninjaRepo;
	
	private static final int PAGE_SIZE = 5;
	
	public Page<Ninja> ninjasPerPage(int pageNumber){
		PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "lastName");
		Page<Ninja> ninjas = ninjaRepo.findAll(pageRequest);
		return ninjaRepo.findAll(pageRequest);
	}
	
	
	public Page<Object[]> dojosAndNinjasPerPage(int pageNumber){
		PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "name");
		Page<Object[]> dojoninja = ninjaRepo.joinDojosAndNinjas(pageRequest);
		return ninjaRepo.joinDojosAndNinjas(pageRequest);
	}
}
