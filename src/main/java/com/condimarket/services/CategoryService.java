package com.condimarket.services;

import com.condimarket.dto.CategoryDto;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryDto categoryDto);
    Optional<CategoryDto> findByCategoryName(String categoryName);
}
