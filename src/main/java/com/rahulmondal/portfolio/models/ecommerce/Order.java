package com.rahulmondal.portfolio.models.ecommerce;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ecommerce_orders")
@Getter
@Setter
@Table(name = "ecommerce_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne   
    @JoinColumn(name = "cart_id",nullable = false)
    private Cart cart;

    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private double totalCost;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
