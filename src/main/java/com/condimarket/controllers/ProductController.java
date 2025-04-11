package com.condimarket.controllers;

import com.condimarket.dto.ProductDto;
import com.condimarket.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }
}
