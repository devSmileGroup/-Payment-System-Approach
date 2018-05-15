package com.smilestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smilestore.model.Order;
import com.smilestore.service.PayService;

@RestController
@RequestMapping(value = "/payment")
public class PayController {

	@Autowired
	private PayService payService;

	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ResponseEntity<?> pay(Order order) throws Exception {
		try {
			return new ResponseEntity<>(payService.pay(order), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public ResponseEntity<?> result(@RequestParam(value = "data") String data,
			@RequestParam(value = "signature") String signature) {

		try {
			payService.payRes(data, signature);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
