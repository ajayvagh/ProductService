package com.scaler.ProductService.services;

import com.scaler.ProductService.exceptions.ProductNotFoundException;
import com.scaler.ProductService.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface ProductService {

    Product getProductById(UUID id) throws ProductNotFoundException;
    Product createProduct(String name, String category, String description);
    List<Product> getAllProducts();
    Product updateProduct(UUID id, String name, String category, String description) throws ProductNotFoundException;
    void deleteProduct(UUID id) throws ProductNotFoundException;
    public Page<Product> getAllProductsNew(int pageSize, int pageNum);
}
