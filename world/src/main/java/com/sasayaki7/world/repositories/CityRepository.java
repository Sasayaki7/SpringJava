package com.sasayaki7.world.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sasayaki7.world.models.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long>{
	List<City> findAll();
}
