package com.rahulmondal.portfolio.dto.response.ecommerce;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponseDTO {

    private UUID name;
    private Long quantity;
    private ProductResponseDTO product;
}
