package com.smilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilestore.model.Category;
import com.smilestore.model.Product;
import com.smilestore.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public List<Product> getByCategories(Category... categories) {		
		return productRepository.findByCategoriesIn(categories);
	}
	
	public Product getById(Long id) {
		return productRepository.getOne(id);
	}
	
}
