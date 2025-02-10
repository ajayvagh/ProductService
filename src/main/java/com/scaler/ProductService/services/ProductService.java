package com.scaler.ProductService.services;

import com.scaler.ProductService.exceptions.ProductNotFoundException;
import com.scaler.ProductService.models.Product;

public interface ProductService {

    public Product getProductById(long id) throws ProductNotFoundException;
}
