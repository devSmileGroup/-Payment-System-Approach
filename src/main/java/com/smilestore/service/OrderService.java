package com.smilestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilestore.model.Order;
import com.smilestore.model.Product;
import com.smilestore.model.User;
import com.smilestore.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order getById(Long id) {
		return orderRepository.getOne(id);
	}

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> getByProducts(Product... products) {
		return orderRepository.findByProductIn(products);
	}

	public List<Order> getByUsers(User... users) {
		return orderRepository.findByUserIn(users);
	}

}
