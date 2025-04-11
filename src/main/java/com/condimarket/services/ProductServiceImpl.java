package com.condimarket.services;

import com.condimarket.dto.ProductDto;
import com.condimarket.persistence.entities.Category;
import com.condimarket.persistence.entities.Product;
import com.condimarket.persistence.repositories.CategoryRepository;
import com.condimarket.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .nameProduct(product.getNameProduct())
                        .referenceProduct(product.getReferenceProduct())
                        .amountProduct(product.getAmountProduct())
                        .description(product.getDescription())
                        .stock(product.getStock())
                        .categoryId(product.getCategory().getId())
                        .categoryName(product.getCategory().getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Product product = Product.builder()
                .nameProduct(dto.getNameProduct())
                .referenceProduct(dto.getReferenceProduct())
                .amountProduct(dto.getAmountProduct())
                .description(dto.getDescription())
                .stock(dto.getStock())
                .category(category)
                .build();

        Product saved = productRepository.save(product);

        return ProductDto.builder()
                .id(saved.getId())
                .nameProduct(saved.getNameProduct())
                .referenceProduct(saved.getReferenceProduct())
                .amountProduct(saved.getAmountProduct())
                .description(saved.getDescription())
                .stock(saved.getStock())
                .categoryId(saved.getCategory().getId())
                .build();
    }
}
