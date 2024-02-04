package com.codingninja.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRq {
	private Long productId;
	private Long totalAmount;
	private Long quantity;
	private PaymentMode paymentMode;
}
