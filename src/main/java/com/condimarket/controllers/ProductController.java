package com.condimarket.controllers;

import com.condimarket.dto.ProductDto;
import com.condimarket.services.CategoryService;
import com.condimarket.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @GetMapping("/filter")
    public List<ProductDto> filterProducts(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "name", required = false) String name
    ) {
        Long categoryId = null;

        if (category != null && !category.isEmpty()) {
            categoryId = categoryService.findByCategoryName(category)
                    .map(catDto -> catDto.getId())
                    .orElse(null);
        }

        return productService.filterProducts(categoryId, name);
    }
}
