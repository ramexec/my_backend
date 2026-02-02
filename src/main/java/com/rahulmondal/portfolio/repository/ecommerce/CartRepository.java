package com.rahulmondal.portfolio.repository.ecommerce;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulmondal.portfolio.models.ecommerce.Cart;

public interface CartRepository extends JpaRepository<Cart,UUID> {

    Optional<Cart> findByUserId(long id);

}
