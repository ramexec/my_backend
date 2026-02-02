package com.rahulmondal.portfolio.dto.requests.ecommerce;

import lombok.Data;

@Data
public class AddToCartRequestDTO {

    private Long productId;
    private Long quantity;
}
