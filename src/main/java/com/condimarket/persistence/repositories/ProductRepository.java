package com.condimarket.persistence.repositories;

import com.condimarket.persistence.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameProductContainingIgnoreCase(String name);
    List<Product> findByCategoryIdAndNameProductContainingIgnoreCase(Long categoryId, String name);
}
