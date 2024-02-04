package com.codingninja.orderservice.external.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "PRODUCT-SERVICE/product/v1")
@Service
@CircuitBreaker(name = "external", fallbackMethod = "fallbackMethod")
public interface ProductService {

	@PutMapping("/reduce/qunatity/{id}")
    ResponseEntity<HttpStatus> reduceQuantity(@PathVariable("id") Long productId, @RequestParam Long quantity);
	
	default void fallbackMethod() {
		throw new RuntimeException("Payment Service is not available");
	}
}
