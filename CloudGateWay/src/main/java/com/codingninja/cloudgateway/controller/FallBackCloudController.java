package com.codingninja.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackCloudController {

	@GetMapping("/orderServiceFallback")
	public String orderFallback() {
		return "Order Service is Down";
	}
	
	@GetMapping("/productServiceFallback")
	public String productFallback() {
		return "Product Service is Down";
	}
	
	@GetMapping("/paymentServiceFallback")
	public String paymentFallback() {
		return "Payment Service is Down";
	}
}
