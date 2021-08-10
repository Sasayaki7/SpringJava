package com.sasayaki7.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sasayaki7.relationships.models.License;
import com.sasayaki7.relationships.repositories.LicenseRepository;

@Service
public class LicenseService {
	private final LicenseRepository personRepo;
	public LicenseService(LicenseRepository personRepo) {
		this.personRepo = personRepo;
	}
	
	public List<License> allLicenses(){
		return personRepo.findAll();
	}
	
	public License createLicense(License b) {
		return personRepo.save(b);
	}
	
	public License findLicense(Long id) {
		Optional<License> optionalLicense = personRepo.findById(id);
		if(optionalLicense.isPresent()) {
			return optionalLicense.get();
		}
		else {
			return null;
		}
	}
	
	public License updateLicense(License b) {
		return personRepo.save(b);
	}
	
	
	public void deleteLicense(Long id) {
		personRepo.deleteById(id);
	}
}
