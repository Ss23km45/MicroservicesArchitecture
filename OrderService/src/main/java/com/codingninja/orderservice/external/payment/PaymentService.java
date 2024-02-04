package com.codingninja.orderservice.external.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codingninja.orderservice.model.PaymentRq;
import com.codingninja.orderservice.model.PaymentRs;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient("PAYMENT-SERVICE/paymentservice/v1")
@CircuitBreaker(name = "external", fallbackMethod = "fallbackMethod")
public interface PaymentService {
	@PostMapping("/makepayment")
	public ResponseEntity<PaymentRs> doPayment(@RequestBody PaymentRq paymentRq);
	
	default void fallbackMethod() {
		throw new RuntimeException("Payment Service is not available");
	}
}
