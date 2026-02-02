package com.rahulmondal.portfolio.repository.ecommerce;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulmondal.portfolio.models.ecommerce.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,UUID>{

    Optional<CartItem> findByCartIdAndProductId(UUID id, Long id2);

}
