package com.rahulmondal.portfolio.dto.response.ecommerce;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersResponseDTO {

    private UUID id;
    private List<CartItemResponseDTO> cartItems;

    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private double totalCost;

    private String status;
}
