package com.sasayaki7.productmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.productmanager.models.Category;
import com.sasayaki7.productmanager.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	public List<Product> findAll();
    List<Product> findByCategoriesNotContains(Category cat);

}
