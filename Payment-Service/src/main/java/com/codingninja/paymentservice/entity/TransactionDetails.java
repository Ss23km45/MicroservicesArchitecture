package com.codingninja.paymentservice.entity;

import java.time.Instant;

import com.codingninja.paymentservice.model.PaymentMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "TRANSACTION_DETAILS")
public class TransactionDetails {
	@Id
	@Column(name= "PAYMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long paymentId;
	@Column(name = "ORDER_ID")
	private Long orderId;
	@Column(name = "PAYMENT_MODE")
	private String paymentMode;
	@Column(name = "REFERENCE_NUMBER")
	private String referenceNumber;
	@Column(name = "PAYMENT_DATE")
	private Instant paymentDate;
	@Column(name = "PAYMENT_STATUS")
	private String paymentStatus;
	@Column(name = "AMOUNT")
	private Long amount;	

}
