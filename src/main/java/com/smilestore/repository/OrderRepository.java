package com.smilestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smilestore.model.Order;
import com.smilestore.model.Product;
import com.smilestore.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByUserIn(User... users);
	List<Order> findByProductIn(Product... products);
	
}
