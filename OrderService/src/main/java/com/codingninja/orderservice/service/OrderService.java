package com.codingninja.orderservice.service;

import org.springframework.http.ResponseEntity;

import com.codingninja.orderservice.model.OrderRq;
import com.codingninja.orderservice.model.OrderRs;

public interface OrderService {

	ResponseEntity<OrderRs> placeOrder(OrderRq orderRq);

}
