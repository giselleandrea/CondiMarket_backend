package com.condimarket.persistence.repositories;

import com.condimarket.persistence.entities.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategory(String category);
}
