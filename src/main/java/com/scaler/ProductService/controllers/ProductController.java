package com.scaler.ProductService.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

//    GET /products/{product_id}
    @GetMapping("/product_id")
    public ResponseEntity<String> getProductById(@PathVariable int productId ) {
        System.out.println("productId: " + productId);
        return new ResponseEntity<String>("product_id :" + productId, HttpStatusCode.valueOf(200));
    }
}
