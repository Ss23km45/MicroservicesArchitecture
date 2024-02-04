package com.codingninja.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingninja.orderservice.model.OrderRq;
import com.codingninja.orderservice.model.OrderRs;
import com.codingninja.orderservice.service.OrderService;

@RestController
@RequestMapping("/orderservice/v1")
public class OrderServiceController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/placeorder")
	public ResponseEntity<OrderRs> placeOrder(@RequestBody OrderRq orderRq){
		
		ResponseEntity<OrderRs> returnRsEn = orderService.placeOrder(orderRq);
		
		return returnRsEn;
	}

}
