package com.condimarket.services;

import com.condimarket.dto.CategoryDto;
import com.condimarket.persistence.entities.Category;
import com.condimarket.persistence.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(cat -> CategoryDto.builder()
                        .id(cat.getId())
                        .category(cat.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        Category category = Category.builder()
                .category(dto.getCategory())
                .build();

        Category saved = categoryRepository.save(category);

        return CategoryDto.builder()
                .id(saved.getId())
                .category(saved.getCategory())
                .build();
    }

    @Override
    public Optional<CategoryDto> findByCategoryName(String categoryName) {
        return categoryRepository.findAll().stream()
                .filter(cat -> cat.getCategory().equalsIgnoreCase(categoryName))
                .findFirst()
                .map(cat -> CategoryDto.builder()
                        .id(cat.getId())
                        .category(cat.getCategory())
                        .build());
}
}
