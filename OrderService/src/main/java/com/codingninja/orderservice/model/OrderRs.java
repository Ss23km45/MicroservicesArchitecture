package com.codingninja.orderservice.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderRs {
	private Long orderId;
	private String orderStatus;
	private String trackingId;
}
