package com.codingninja.productservice.controller;

import com.codingninja.productservice.entity.Product;
import com.codingninja.productservice.model.ProductServiceRq;
import com.codingninja.productservice.model.ProductServiceRs;
import com.codingninja.productservice.service.ProductService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product/v1")
@Slf4j
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class Controller {
    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<HttpStatus> addProduct(@RequestBody ProductServiceRq requ){
        log.error("InComing Request " + requ);
        productService.addProduct(new Product(requ.getProductName(), requ.getQuantity(), requ.getPrice()));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getproduct/{id}")
    public ResponseEntity<ProductServiceRs> getProduct(@PathVariable("id") Long productId){
        ProductServiceRs rs = productService.getProduct(productId);

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    
    @PutMapping("/reduce/qunatity/{id}")
    public ResponseEntity<HttpStatus> reduceQuantity(@PathVariable("id") Long productId, @RequestParam Long quantity){
    	
    	HttpStatus status = productService.reduceQuantity(productId, quantity);
    	
    	return new ResponseEntity<>(status);
    }
    
    @GetMapping("/getAllProducts")
    public List<ProductServiceRs> getAllProducts(){
    	
    	return productService.getAllProducts();
    }
}
