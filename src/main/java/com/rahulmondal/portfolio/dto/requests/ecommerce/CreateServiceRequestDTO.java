package com.rahulmondal.portfolio.dto.requests.ecommerce;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CreateServiceRequestDTO {
     
    private String title;
    private String description;
    private String price;
    private boolean featured;
}
