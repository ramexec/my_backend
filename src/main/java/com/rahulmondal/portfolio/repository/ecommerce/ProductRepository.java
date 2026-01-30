package com.rahulmondal.portfolio.repository.ecommerce;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulmondal.portfolio.models.ecommerce.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByisFeaturedTrue();

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
