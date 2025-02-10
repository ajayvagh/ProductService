package com.scaler.ProductService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    long id;
    String title;
    String price;
    String description;
    String category;
    String image;
}
