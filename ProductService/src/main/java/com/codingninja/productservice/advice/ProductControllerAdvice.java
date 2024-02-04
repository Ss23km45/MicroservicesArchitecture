package com.codingninja.productservice.advice;

import com.codingninja.productservice.exception.ProductNotFound;
import com.codingninja.productservice.exception.ProductNotFoundCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundCustomException.class)
    public ResponseEntity<ProductNotFound> throwThisError(ProductNotFoundCustomException exception){
        ProductNotFound productNotFound =new ProductNotFound();
        productNotFound.setErrorCode(exception.getErrorCode());
        productNotFound.setErrorMessage(exception.getMessage());

        return new ResponseEntity<>(productNotFound, HttpStatus.NOT_FOUND);

    }
}
