package com.scaler.ProductService.services;

import com.scaler.ProductService.dtos.FakeStoreProductDto;
import com.scaler.ProductService.exceptions.ProductNotFoundException;
import com.scaler.ProductService.models.Product;
import com.scaler.ProductService.repository.ProductRepository;
//import org.hibernate.query.Page;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service("FakeStore")
public class FakeStoreProductService implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
        Product product = (Product) this.redisTemplate.opsForHash().get("PRODUCTS", "product_" + id);
        if(product != null) {
            return product;
        }

        String url = "https://fakestoreapi.com/products/" + id;

        FakeStoreProductDto fakeStoreProductDto = this.restTemplate.getForObject(url, FakeStoreProductDto.class);
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id :" + id + " not found");
        }
        product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        this.redisTemplate.opsForHash().put("PRODUCTS", "product_" + id, product);
        return product;
    }

    @Override
    public Product createProduct(String name, String category, String description) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(UUID id, String name, String category, String description) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void deleteProduct(UUID id) throws ProductNotFoundException {
    }

    @Override
    public Page<Product> getAllProductsNew(int pageSize, int pageNum) {
        return null;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setName(dto.getTitle());

        return product;
    }



}
