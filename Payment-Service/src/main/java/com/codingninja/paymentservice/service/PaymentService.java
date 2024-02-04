package com.codingninja.paymentservice.service;

import org.springframework.http.ResponseEntity;

import com.codingninja.paymentservice.model.PaymentRq;
import com.codingninja.paymentservice.model.PaymentRs;

public interface PaymentService {

	ResponseEntity<PaymentRs> makePayment(PaymentRq paymentRq);

}
