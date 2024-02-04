package com.codingninja.productservice.service.impl;

import com.codingninja.productservice.entity.Product;
import com.codingninja.productservice.exception.ProductNotFoundCustomException;
import com.codingninja.productservice.model.ProductServiceRq;
import com.codingninja.productservice.model.ProductServiceRs;
import com.codingninja.productservice.repository.ProductRepository;
import com.codingninja.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;

    @Override
    public void addProduct(Product product) {
        try{
            repository.save(product);
        }catch(Exception e){
//            log.error("Exception while saving entity " + e.getMessage());
        }
    }

    @Override
    public ProductServiceRs getProduct(Long id){

        ProductServiceRs serviceRs = new ProductServiceRs();

           Product pr=  repository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundCustomException("Product not found in Database", id+""));
           serviceRs.setPrice(pr.getPrice());
           serviceRs.setProductId(pr.getProductId());
           serviceRs.setProductName(pr.getProductName());
           serviceRs.setQuantity(pr.getQuantity());

            return serviceRs;

    }

	@Override
	public HttpStatus reduceQuantity(Long productId, Long quantity) {
		// TODO Auto-generated method stub
		//Get the Product from DB first
		log.error("Product with id {} and Quantity {}", productId, quantity);
		Product product = repository.findById(productId)
					.orElseThrow(() -> new ProductNotFoundCustomException("Product you are looking for is not found", "NOT_FOUND"));
		if(product.getQuantity() < quantity) {
			throw new RuntimeException("Out of Stock");
		}
		
		product.setQuantity(product.getQuantity() - quantity);
		repository.save(product);
		
		return HttpStatus.OK;
	}

	@Override
	public List<ProductServiceRs> getAllProducts() {
		// TODO Auto-generated method stub
		List<ProductServiceRs> responseList = new ArrayList<>();
			List<Product> listOfProducts = repository.findAll();
			for (Product product : listOfProducts) {
				ProductServiceRs rs= new ProductServiceRs();
				BeanUtils.copyProperties(product, rs);
				log.info("After Copying properties to Response Object "  + rs);
				responseList.add(rs);
			}
		
		
		return responseList;
	}
}
