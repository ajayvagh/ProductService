package com.scaler.ProductService.services;

import com.scaler.ProductService.dtos.FakeStoreProductDto;
import com.scaler.ProductService.exceptions.ProductNotFoundException;
import com.scaler.ProductService.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        String url = "https://fakestoreapi.com/products/" + id;
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(url, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id :" + id + " not found");
        }

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setName(dto.getTitle());

        return product;
    }
}
