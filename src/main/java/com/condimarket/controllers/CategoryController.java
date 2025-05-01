package com.condimarket.controllers;

import com.condimarket.dto.CategoryDto;
import com.condimarket.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }
}
