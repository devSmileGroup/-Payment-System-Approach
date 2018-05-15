package com.smilestore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smilestore.exception.UserException;
import com.smilestore.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		com.smilestore.model.User user = new com.smilestore.model.User();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid com.smilestore.model.User appUser, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			userService.save(appUser);
			
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new com.smilestore.model.User());
			modelAndView.setViewName("registration");
		}  catch (UserException e) {
			bindingResult.rejectValue("nickName", "error.nickName", e.getMessage());
		} catch (Exception e) {
			bindingResult.rejectValue("nickName", "error.nickName", e.getMessage());
		}
			
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("loginPage");
		
		return modelAndView;
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public ModelAndView logoutSuccessfulPage() {		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "Logout");
		modelAndView.setViewName("logoutSuccessfulPage");
		
		return modelAndView;
	}
	
}
