package com.rahulmondal.portfolio.dto.requests.ecommerce;

import lombok.Data;

@Data
public class CreateProductRequestDTO {

    private String name;
    private String description;
    private String image;
    private int rating;
    private int price;
    private int discount;
    private boolean isFeatured;

    private long categoryId;
}
