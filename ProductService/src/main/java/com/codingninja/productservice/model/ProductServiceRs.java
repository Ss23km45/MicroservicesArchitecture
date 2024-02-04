package com.codingninja.productservice.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductServiceRs {
    private Long price;
    private String productName;
    private Long quantity;
    private Long productId;
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
