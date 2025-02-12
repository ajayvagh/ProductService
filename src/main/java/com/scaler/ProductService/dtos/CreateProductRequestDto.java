package com.scaler.ProductService.dtos;

import lombok.Data;

@Data
public class CreateProductRequestDto {
    String name;
    String category;
    String description;
}
