package com.codingninja.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingninja.paymentservice.entity.TransactionDetails;

@Repository
public interface PaymentRespository extends JpaRepository<TransactionDetails, Long>{

}
