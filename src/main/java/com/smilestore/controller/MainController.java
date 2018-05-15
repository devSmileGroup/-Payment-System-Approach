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
public class MainController {

	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("title", "Welcome");
		modelAndView.addObject("message", "This is welcome page!");
		modelAndView.setViewName("welcomePage");
		
		return modelAndView;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied(Principal principal) {		
		ModelAndView modelAndView = new ModelAndView();

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtils.toString(loginedUser);

			modelAndView.addObject("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("403Page");
		}
		
		return modelAndView;
	}

}
