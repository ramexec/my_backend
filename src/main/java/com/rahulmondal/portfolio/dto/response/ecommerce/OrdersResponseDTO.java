package com.rahulmondal.portfolio.dto.response.ecommerce;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class OrdersResponseDTO {

    private UUID id;
    private CartItemResponseDTO cartItems;

    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private double totalCost;

    private String status;
}
