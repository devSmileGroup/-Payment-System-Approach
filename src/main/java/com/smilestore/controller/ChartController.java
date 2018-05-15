package com.smilestore.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/chart")
public class ChartController {
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView login() throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart");
		
		return modelAndView;
	}

	@RequestMapping(value = {"/getfirst"}, method = RequestMethod.GET)
	public String getFirst() throws IOException {

		return readFile("first_line.json");
	}
	
	@RequestMapping(value = {"/getsecond"}, method = RequestMethod.GET)
	public String getSecond() throws IOException {

		return readFile("second_line.json");
	}
	
	@RequestMapping(value = {"/getthird"}, method = RequestMethod.GET)
	public String getThird() throws IOException {

		return readFile("third_line.json");
	}

	public String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			return everything;
		} finally {
			br.close();
		}
	}
}
