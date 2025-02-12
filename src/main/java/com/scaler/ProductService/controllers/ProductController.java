package com.scaler.ProductService.controllers;

import com.scaler.ProductService.dtos.CreateProductRequestDto;
import com.scaler.ProductService.exceptions.InvalidRequestException;
import com.scaler.ProductService.exceptions.ProductNotFoundException;
import com.scaler.ProductService.models.Product;
import com.scaler.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("dbImpl")
    private ProductService productService;

//    GET /products/{product_id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID productId ) throws ProductNotFoundException {
//        if(productId < 1 || productId > 20) {
//            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
//        }
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatusCode.valueOf(200));
    }

    @PostMapping()
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto){
        System.out.println(requestDto);
//        ADD validation
        if (requestDto.getName() == null || requestDto.getName().trim().isEmpty()) {
            throw new InvalidRequestException("Product name cannot be null or empty");
        }

        if (requestDto.getCategory() == null || requestDto.getCategory().trim().isEmpty()) {
            throw new InvalidRequestException("Product category cannot be null or empty");
        }

        if (requestDto.getDescription() == null || requestDto.getDescription().trim().isEmpty()) {
            throw new InvalidRequestException("Product description cannot be null or empty");
        }

        return productService.createProduct(requestDto.getName(), requestDto.getCategory(), requestDto.getDescription());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable UUID id, @RequestBody CreateProductRequestDto requestDto) throws ProductNotFoundException {
        return productService.updateProduct(id, requestDto.getName(), requestDto.getCategory(), requestDto.getDescription());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) throws ProductNotFoundException {
        productService.deleteProduct(id);
    }


}
