package com.scaler.ProductService.services;

import com.scaler.ProductService.exceptions.ProductNotFoundException;
import com.scaler.ProductService.models.Product;
import com.scaler.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("dbImpl")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(String name, String category, String description) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(UUID id, String name, String category, String description) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID id) throws ProductNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getAllProductsNew(int pageSize, int pageNum) {
        pageSize = Math.min(pageSize, 20);

        return productRepository.findAll(PageRequest.of(pageNum, pageSize,
                Sort.by("name").ascending().and(
                        Sort.by("category").descending()
                )));
    }

}
