package com.sasayaki7.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sasayaki7.dojosandninjas.models.Dojo;
import com.sasayaki7.dojosandninjas.models.Ninja;
import com.sasayaki7.dojosandninjas.repositories.DojoRepository;
import com.sasayaki7.dojosandninjas.repositories.NinjaRepository;

@Service
public class ApiServices {
	
	private DojoRepository dojoRep;
	private NinjaRepository ninjaRep;
	public ApiServices(DojoRepository dojoRep, NinjaRepository ninjaRep) {
		this.dojoRep = dojoRep;
		this.ninjaRep = ninjaRep;
	}
	
	public List<Dojo> findAllDojos(){
		return dojoRep.findAll();
	}
	
	public List<Ninja> findAllNinjas(){
		return ninjaRep.findAll();
	}
	
	public Ninja getNinja(Long id) {
		Optional<Ninja> tempNinja = ninjaRep.findById(id);
		if (tempNinja.isPresent()) {
			return tempNinja.get();
		} else {
			return null;
		}
	}
	
	public Dojo getDojo(Long id) {
		Optional<Dojo> tempDojo = dojoRep.findById(id);
		if (tempDojo.isPresent()) {
			return tempDojo.get();
		} else {
			return null;
		}
	}
	
	public Ninja  createNinja(Ninja n) {
		return ninjaRep.save(n);
	}
	
	
	public Ninja updateNinja(Ninja n) {
		return ninjaRep.save(n);
	}
	
	
	public void deleteNinja(Long id) {
		ninjaRep.deleteById(id);
	}
	
	public Dojo createDojo(Dojo d) {
		return dojoRep.save(d);
	}
	
	
	public Dojo updateDojo(Dojo d) {
		return dojoRep.save(d);
	}
	
	
	public void deleteDojo(Long id) {
		dojoRep.deleteById(id);
	}
	
}
