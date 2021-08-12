package com.sasayaki7.productmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sasayaki7.productmanager.models.Category;
import com.sasayaki7.productmanager.models.Product;
import com.sasayaki7.productmanager.repositories.CategoryRepository;
import com.sasayaki7.productmanager.repositories.ProductRepository;

@Service
public class ApiServices {
	private final ProductRepository prodRepo;
	private final CategoryRepository categoryRepo;
	
	public ApiServices(ProductRepository prodRepo, CategoryRepository categoryRepo) {
		this.prodRepo = prodRepo;
		this.categoryRepo = categoryRepo;
	}
	
	
	public List<Category> getCategories(){
		return categoryRepo.findAll();
	}
	
	
	public List<Product> getProducts(){
		return prodRepo.findAll();
	}
	
	public Product createProduct(Product p) {
		return prodRepo.save(p);
	}
	
	public Product updateProduct(Product p) {
		return prodRepo.save(p);
	}
	
	public Category createCategory(Category c) {
		return categoryRepo.save(c);
	}
	
	public Category updateCategory(Category c) {
		return categoryRepo.save(c);
	}
	
	public Category findCategory(Long id) {
		Optional<Category> optCategory = categoryRepo.findById(id);
		if(optCategory.isPresent()) {
			return optCategory.get();
		}
		else {
			return null;
		}
	}

	public Product findProduct(Long id) {
		Optional<Product> optProduct = prodRepo.findById(id);
		if(optProduct .isPresent()) {
			return optProduct.get();
		}
		else {
			return null;
		}
	}
	
	public void deleteCategory(Long id) {
		categoryRepo.deleteById(id);
	}
	
	public void deleteProduct(Long id) {
		prodRepo.deleteById(id);
	}
	
	public List<Category> findCategoriesNotWithProduct(Product p){
		return categoryRepo.findByProductsNotContains(p);
	}
	
	public List<Product> findProductsNotInCategory(Category c){
		return prodRepo.findByCategoriesNotContains(c);
	}
	
}
