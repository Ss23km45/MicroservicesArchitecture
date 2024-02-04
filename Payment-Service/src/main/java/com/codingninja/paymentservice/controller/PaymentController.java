package com.codingninja.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingninja.paymentservice.model.PaymentRq;
import com.codingninja.paymentservice.model.PaymentRs;
import com.codingninja.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/paymentservice/v1")
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/makepayment")
	public ResponseEntity<PaymentRs> doPayment(@RequestBody PaymentRq paymentRq){
		return paymentService.makePayment(paymentRq);
	}

}
