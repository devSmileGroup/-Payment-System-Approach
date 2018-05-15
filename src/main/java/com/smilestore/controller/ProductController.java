package com.smilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smilestore.model.Product;
import com.smilestore.service.ProductService;

@RestController
@RequestMapping(value = "product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/all")
	public ModelAndView products() {
		List<Product> products = productService.getAll();

		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.addObject("products", products);
		modelAndView.setViewName("product");

		return modelAndView;
	}

}
