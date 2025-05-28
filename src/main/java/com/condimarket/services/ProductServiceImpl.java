package com.condimarket.services;

import com.condimarket.dto.ProductDto;
import com.condimarket.persistence.entities.Category;
import com.condimarket.persistence.entities.Product;
import com.condimarket.persistence.repositories.CategoryRepository;
import com.condimarket.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
        }

        @Override
        public ProductDto createProduct(ProductDto productDto) {
                Optional<Category> categoryOpt = categoryRepository.findById(productDto.getCategoryId());

                if (categoryOpt.isEmpty()) {
                throw new RuntimeException("Categoría no encontrada");
                }

                Product product = Product.builder()
                        .nameProduct(productDto.getNameProduct())
                        .referenceProduct(productDto.getReferenceProduct())
                        .amountProduct(productDto.getAmountProduct())
                        .description(productDto.getDescription())
                        .stock(productDto.getStock())
                        .image(productDto.getImage())
                        .category(categoryOpt.get())
                        .build();

                Product saved = productRepository.save(product);
                return mapToDto(saved);
        }

        @Override
        public List<ProductDto> filterProducts(Long categoryId, String name) {
                List<Product> filtered;

                if (categoryId != null && name != null) {
                filtered = productRepository.findByCategoryIdAndNameProductContainingIgnoreCase(categoryId, name);
                } else if (categoryId != null) {
                filtered = productRepository.findByCategoryId(categoryId);
                } else if (name != null) {
                filtered = productRepository.findByNameProductContainingIgnoreCase(name);
                } else {
                filtered = productRepository.findAll();
                }

                return filtered.stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
        }

        private ProductDto mapToDto(Product product) {
                return ProductDto.builder()
                        .id(product.getId())
                        .nameProduct(product.getNameProduct())
                        .referenceProduct(product.getReferenceProduct())
                        .amountProduct(product.getAmountProduct())
                        .description(product.getDescription())
                        .stock(product.getStock())
                        .image(product.getImage())
                        .categoryId(product.getCategory().getId())
                        .build();
        }
}
