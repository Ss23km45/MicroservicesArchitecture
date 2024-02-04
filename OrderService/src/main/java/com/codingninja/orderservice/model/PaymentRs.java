package com.codingninja.orderservice.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentRs {
	private String paymentStatus;
	private String paymentReferenceNumber;
}
