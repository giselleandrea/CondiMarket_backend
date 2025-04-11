package com.condimarket.services;

import com.condimarket.dto.ProductDto;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> filterProducts(Long categoryId, String name);
}
