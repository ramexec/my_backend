package com.rahulmondal.portfolio.repository.ecommerce;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahulmondal.portfolio.models.ecommerce.Order;

public interface OrderRepository extends JpaRepository<Order,UUID>{

}
