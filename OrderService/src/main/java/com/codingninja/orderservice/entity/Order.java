package com.codingninja.orderservice.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ORDER_DETAILS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private Long orderId;
	@Column(name = "PRODUCT_ID")
	private Long productId;
	@Column(name = "QUANTITY")
	private Long quantity;
	@Column(name = "ORDER_DATE")
	private Instant orderDate;
	@Column(name = "ORDER_STATUS")
	private String orderStatus;
	@Column(name = "AMOUNT")
	private Long amount;
	@Column(name = "TRACKING_ID")
	private String trackingId;

}
