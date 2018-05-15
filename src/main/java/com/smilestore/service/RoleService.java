package com.smilestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilestore.model.Role;
import com.smilestore.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role getRoleByName(String name) {
		return roleRepository.findOneByName(name);
	}
	
}
