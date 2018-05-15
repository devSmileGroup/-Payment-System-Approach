package com.smilestore.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smilestore.util.WebUtils;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView adminPage(Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userInfo", userInfo);
		modelAndView.setViewName("adminPage");

		return modelAndView;
	}

}
