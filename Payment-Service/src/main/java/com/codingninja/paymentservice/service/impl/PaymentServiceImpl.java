package com.codingninja.paymentservice.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codingninja.paymentservice.entity.TransactionDetails;
import com.codingninja.paymentservice.model.PaymentRq;
import com.codingninja.paymentservice.model.PaymentRs;
import com.codingninja.paymentservice.repository.PaymentRespository;
import com.codingninja.paymentservice.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRespository paymentRepository;

	@Override
	public ResponseEntity<PaymentRs> makePayment(PaymentRq paymentRq) {
		// TODO Auto-generated method stub
		TransactionDetails txnDetail = null;
		try {
											txnDetail 	= TransactionDetails.builder()
															.amount(paymentRq.getAmount())
															.orderId(paymentRq.getOrderId())
															.paymentDate(Instant.now())
															.paymentMode(paymentRq.getPaymentMode().toString())
															.paymentStatus("SUCCESS")
															.referenceNumber(UUID.randomUUID().toString())
															.build();
			paymentRepository.save(txnDetail);
			log.error("Payment Successful " + txnDetail);												
		}catch(Exception ee) {
			log.error("Exception While Saving Payment Data to DB" + ee.getMessage());
			return new ResponseEntity<PaymentRs>(PaymentRs.builder()
														.paymentReferenceNumber(txnDetail.getReferenceNumber())
														.paymentStatus("FAILED").build(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return new ResponseEntity<PaymentRs>(PaymentRs.builder()
				.paymentReferenceNumber(txnDetail.getReferenceNumber())
				.paymentStatus("SUCCESS").build(), HttpStatus.OK);
	}

}
