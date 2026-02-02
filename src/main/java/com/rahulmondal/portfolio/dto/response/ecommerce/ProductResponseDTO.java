package com.rahulmondal.portfolio.dto.response.ecommerce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {

    private Long id;

    private String name;
    private String description;
    private String image;
    private int rating;
    private int price;
    private int discount;
    
    private boolean isFeatured;
    private String categoryName;

    private Long categoryId;
}
