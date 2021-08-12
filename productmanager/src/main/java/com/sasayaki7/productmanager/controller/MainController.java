package com.sasayaki7.productmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sasayaki7.productmanager.models.Category;
import com.sasayaki7.productmanager.models.Product;
import com.sasayaki7.productmanager.services.ApiServices;


@Controller
public class MainController {
	
	private final ApiServices api;
	public MainController(ApiServices api) {
		this.api = api;
	}
	
	@RequestMapping("/products/new")
	public String productForm(Model model) {
		model.addAttribute("product", new Product());
		return "productform.jsp";
	}

	
	@RequestMapping(value="/products/new", method=RequestMethod.POST)
	public String newProduct(@Valid @ModelAttribute("product") Product prod, BindingResult result) {
		if (result.hasErrors()) {
			return "productform.jsp";
		}
		else {
			api.createProduct(prod);
			return "redirect:/products/new";
		}
	}

	
	@RequestMapping("/categories/new")
	public String categoryForm(Model model) {
		model.addAttribute("category", new Category());
		return "categoryform.jsp";
	}


	@RequestMapping(value="/categories/new", method=RequestMethod.POST)
	public String newCategory(@Valid @ModelAttribute("category") Category cat, BindingResult result) {
		if (result.hasErrors()) {
			return "categoryform.jsp";
		}
		else {
			api.createCategory(cat);
			return "redirect:/categories/new";
		}
	}
	
	@RequestMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model) {
		Product prod = api.findProduct(id);
		List<Category> catList = api.findCategoriesNotWithProduct(prod);
		model.addAttribute("product", prod);
		model.addAttribute("categoriesNotInProduct", catList);
		return "showproduct.jsp";
	}
	
	@RequestMapping(value="/products/{id}/addCategory", method=RequestMethod.POST)
	public String addCategoryToProduct(@PathVariable("id") Long id, @RequestParam("category") Long catId,  Model model) {
		Product prod = api.findProduct(id);
		List<Category> catList = prod.getCategories();
		catList.add(api.findCategory(catId));
		prod.setCategories(catList);
		api.updateProduct(prod);
		return "redirect:/products/"+id;
	}
	
	@RequestMapping("/categories/{id}")
	public String showCategory(@PathVariable("id") Long id, Model model) {
		Category cat = api.findCategory(id);
		List<Product> catList = api.findProductsNotInCategory(cat);
		model.addAttribute("category", cat);
		model.addAttribute("productsNotInCategory", catList);
		return "showcategory.jsp";
	}
	
	@RequestMapping(value="/categories/{id}/addProduct", method=RequestMethod.POST)
	public String addProductToCategory(@PathVariable("id") Long id, @RequestParam("product") Long prodId,  Model model) {
		Category cat = api.findCategory(id);
		List<Product> prodList = cat.getProducts();
		prodList.add(api.findProduct(prodId));
		cat.setProducts(prodList);
		api.updateCategory(cat);
		return "redirect:/categories/"+id;
	}
	
}
