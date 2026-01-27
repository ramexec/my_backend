package com.rahulmondal.portfolio.dto.requests.ecommerce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCategoryRequestDTO {
    private String name;    
}
