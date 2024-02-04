package com.codingninja.productservice.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

public class ProductNotFoundCustomException extends RuntimeException{

    private String errorCode;

    public ProductNotFoundCustomException(String errorMsg, String errorCode){
        super(errorMsg);
        this.errorCode = errorCode;
    }

	public String getErrorCode() {
		// TODO Auto-generated method stub
		return errorCode;
	}
}
