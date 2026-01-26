package com.rahulmondal.portfolio.dto;

import org.springframework.stereotype.Component;

import com.rahulmondal.portfolio.dto.ecommerce.response.ServicesProvidedDTO;
import com.rahulmondal.portfolio.models.ecommerce.ServicesProvided;

@Component
public class DTOmapper {

    public ServicesProvidedDTO toServiceDto(ServicesProvided entity) {
        ServicesProvidedDTO dto = new ServicesProvidedDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setFeatured(entity.isFeatured());
        return dto;
    }
}
