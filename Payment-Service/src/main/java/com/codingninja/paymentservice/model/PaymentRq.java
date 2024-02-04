package com.codingninja.paymentservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRq {
    private Long orderId;
	private Long amount;
	private String referenceNumber;
	private PaymentMode paymentMode;
}
