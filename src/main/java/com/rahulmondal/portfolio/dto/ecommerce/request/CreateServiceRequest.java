package com.rahulmondal.portfolio.dto.ecommerce.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CreateServiceRequest {
     
    private String title;
    private String description;
    private String price;
    private boolean featured;
}
