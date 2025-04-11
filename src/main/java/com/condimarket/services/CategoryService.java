package com.condimarket.services;

import com.condimarket.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryDto categoryDto);
}
