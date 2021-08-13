package com.sasayaki7.world.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sasayaki7.world.models.City;
import com.sasayaki7.world.models.Country;

@Repository
public interface WorldRepository extends CrudRepository<Country, Long>{
	List<Country> findAll();
	
	@Query(value="SELECT countries.name, COUNT(*) AS num_cities FROM countries LEFT JOIN cities ON countries.id = cities.country_id GROUP BY countries.id ORDER BY num_cities DESC", nativeQuery=true)
	List<Object[]> getAllCityCount();
	
	
	@Query("SELECT cit FROM Country country LEFT JOIN country.cities cit WHERE country.name=?1 AND cit.population > ?2 ORDER BY cit.population DESC")
	List<City> getCitiesInCountry(String name, int pop);
}
