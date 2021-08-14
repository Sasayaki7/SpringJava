package com.sasayaki7.dojosandninjas.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sasayaki7.dojosandninjas.models.Ninja;

public interface NinjaRepository extends PagingAndSortingRepository<Ninja, Long> {
	
	@Query("SELECT d, n FROM Dojo d JOIN d.ninjas n")
	Page<Object[]> joinDojosAndNinjas(PageRequest req);
}
