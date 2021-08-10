package com.sasayaki7.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.relationships.models.License;

public interface LicenseRepository extends CrudRepository<License, Long>{
	public List<License> findAll();
}
