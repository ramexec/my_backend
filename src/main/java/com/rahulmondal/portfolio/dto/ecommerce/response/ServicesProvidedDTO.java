package com.rahulmondal.portfolio.dto.ecommerce.response;

import lombok.Data;

@Data
public class ServicesProvidedDTO {
    private Long id;
    private String title;
    private String description;
    private String price;
    private boolean featured;
}
