package com.codingninja.orderservice.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codingninja.orderservice.entity.Order;
import com.codingninja.orderservice.external.payment.PaymentService;
import com.codingninja.orderservice.external.product.ProductService;
import com.codingninja.orderservice.model.OrderRq;
import com.codingninja.orderservice.model.OrderRs;
import com.codingninja.orderservice.model.PaymentRq;
import com.codingninja.orderservice.model.PaymentRs;
import com.codingninja.orderservice.repository.OrderRepository;
import com.codingninja.orderservice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductService productService;
	
	@Autowired
	PaymentService paymentService;
	
	@Override
	public ResponseEntity<OrderRs> placeOrder(OrderRq orderRq) {
		// TODO Auto-generated method stub
		log.error("Inside placeOrder OrderRq " + orderRq);
		//Create UUID as trackingId to our Order
		String trackingId = UUID.randomUUID().toString();
		//Save the Data with status Order Created
		Order myOrder = Order.builder()
							.amount(orderRq.getTotalAmount())
							.orderDate(Instant.now())
							.orderStatus("CREATED")
							.productId(orderRq.getProductId())
							.quantity(orderRq.getQuantity())
							.trackingId(trackingId).build();
		//Make a DB call to store the Data in DB
		Order order = orderRepository.save(myOrder);
		log.error("Order saved to Db with status created orderId created " + order.getOrderId() + " with -- TrackingID " +order.getTrackingId());
		
		// Call Payment service to make payment
		try {
			ResponseEntity<PaymentRs> responsePayment = paymentService.doPayment(PaymentRq.builder().amount(orderRq.getTotalAmount())
																					.orderId(myOrder.getOrderId())
																					.paymentMode(orderRq.getPaymentMode())
																					.referenceNumber(trackingId)
																					.build());
			log.info("Response received from Payment Service after successful call "+ responsePayment);
		}catch(Exception e) {
			// Store the Payment success or Failure in DB 
			log.error("Exception While Calling Payment Service " + e.getMessage());
			order.setOrderStatus("PAYMENT_FAILED");
			orderRepository.save(myOrder);
			return new ResponseEntity<OrderRs>(OrderRs.builder().orderId(order.getOrderId()).orderStatus("PAYMENT_FAILED").trackingId(trackingId).build(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		//Call Product Service to check the quantity of that order and reduce in product server
		try{
			ResponseEntity<HttpStatus> productServiceStatus =  productService.reduceQuantity(orderRq.getProductId(), orderRq.getQuantity());
			log.info("Response received from Product Service after successful call "+ productServiceStatus);

		}catch(Exception e) {
			log.error("Exception While Calling Product Service " + e.getMessage());
			order.setOrderStatus("FAILED_PRODUCT_NOT_AVAILABLE");
			orderRepository.save(myOrder);
			return new ResponseEntity<OrderRs>(OrderRs.builder().orderId(order.getOrderId()).orderStatus("PRODUCT_NOT_AVAILABLE").trackingId(trackingId).build(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		order.setOrderStatus("SUCCESS");
		orderRepository.save(order);
		log.error("Order Placed Successfully" + " ---- " + order);
		// Return the ResponseEntity with TrackingId,orderId
		return new ResponseEntity<OrderRs>(OrderRs.builder().orderId(order.getOrderId()).orderStatus("ORDER PLACED SUCCESSFULLY").trackingId(trackingId).build(), HttpStatus.OK);
	}

}
