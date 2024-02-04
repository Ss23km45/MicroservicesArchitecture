package com.codingninja.productservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.codingninja.productservice.entity.Product;
import com.codingninja.productservice.model.ProductServiceRs;

public interface ProductService {
    void addProduct(Product product);
    ProductServiceRs getProduct(Long id);
	HttpStatus reduceQuantity(Long productId, Long quantity);
	List<ProductServiceRs> getAllProducts();
}
