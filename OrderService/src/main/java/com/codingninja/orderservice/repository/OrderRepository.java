package com.codingninja.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingninja.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
