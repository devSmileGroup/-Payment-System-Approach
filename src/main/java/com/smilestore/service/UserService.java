package com.smilestore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smilestore.controller.ProductController;
import com.smilestore.exception.UserException;
import com.smilestore.model.Role;
import com.smilestore.model.User;
import com.smilestore.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private static final Logger LOGGER = Logger.getLogger(ProductController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User getUserByNickName(String nickName) {
		User user = userRepository.findOneByNickName(nickName);
		
		return user;		
	}
	
	public void save(User user) {	
		if (isExistUser(user)) {
			String errorMessage = String.format("User with nickname: %s is already registered", user.getNickName());
			LOGGER.info(errorMessage);
			throw new UserException(errorMessage);
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList(roleService.getRoleByName("USER")));
		userRepository.save(user);
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = getUserByNickName(userName);

		if (user == null) {
			String message = String.format("User %s was not found in the database", userName);		
			LOGGER.warn(message);
			throw new UsernameNotFoundException(message);
		}

		List<Role> roles = user.getRoles();
		List<GrantedAuthority> grantList = new ArrayList<>();

		if (!roles.isEmpty()) {
			for (Role role : roles) {
				grantList.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		}

		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
				user.getNickName(), user.getPassword(), grantList);

		return userDetails;
	}
	
	private Boolean isExistUser(User user) {
		User appUser = getUserByNickName(user.getNickName());
		
		return appUser != null ? true : false;
	}
	
}

