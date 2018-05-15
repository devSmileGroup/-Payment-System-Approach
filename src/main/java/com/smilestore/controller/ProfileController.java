package com.smilestore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smilestore.service.UserService;

@RestController
@RequestMapping(value = "profile")
public class ProfileController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView userInfo(Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		com.smilestore.model.User user = userService.getUserByNickName(loginedUser.getUsername());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("userInfoPage");

		return modelAndView;
	}
	
}
