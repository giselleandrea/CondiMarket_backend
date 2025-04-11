package com.condimarket.persistence.repositories;

import com.condimarket.persistence.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
