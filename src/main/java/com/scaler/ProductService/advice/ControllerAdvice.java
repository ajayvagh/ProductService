package com.scaler.ProductService.advice;


import com.scaler.ProductService.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> getProductNotFoundException(ProductNotFoundException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
    }

}
