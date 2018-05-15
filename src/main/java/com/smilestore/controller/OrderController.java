package com.smilestore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.smilestore.model.Order;
import com.smilestore.model.Product;
import com.smilestore.service.OrderService;
import com.smilestore.service.ProductService;
import com.smilestore.service.UserService;

@RestController
@RequestMapping(value = "order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView getOrdersByUser(Principal principal) {		
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		com.smilestore.model.User user = userService.getUserByNickName(loginedUser.getUsername());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orders", orderService.getByUsers(user));
		modelAndView.setViewName("orderPage");

		return modelAndView;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newOrder(@RequestParam(value = "product_id") Long id) {		
		ModelAndView modelAndView = new ModelAndView();
		
		Product product = productService.getById(id);
		Order order = new Order();
		order.setProduct(product);
		
		modelAndView.addObject("order", order);
		modelAndView.setViewName("newOrder");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public RedirectView createOrder(Order order, Principal principal, RedirectAttributes attributes) {		
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		com.smilestore.model.User user = userService.getUserByNickName(loginedUser.getUsername());

		order.setUser(user);
		Order newOrder =  orderService.save(order);
		attributes.addFlashAttribute("order", newOrder);		

		return new RedirectView("/payment/pay");
	}
	
}
