package com.scaler.ProductService.controllers;

import com.scaler.ProductService.exceptions.ProductNotFoundException;
import com.scaler.ProductService.models.Product;
import com.scaler.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    GET /products/{product_id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productId ) throws ProductNotFoundException {
//        if(productId < 1 || productId > 20) {
//            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
//        }
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatusCode.valueOf(200));
    }


}
