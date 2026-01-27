package com.rahulmondal.portfolio.repository.ecommerce;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulmondal.portfolio.models.ecommerce.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}
