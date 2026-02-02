package com.rahulmondal.portfolio.dto;

import org.springframework.stereotype.Component;

import com.rahulmondal.portfolio.dto.response.ServicesProvidedDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.CategoryResponseDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.ProductResponseDTO;
import com.rahulmondal.portfolio.models.ServicesProvided;
import com.rahulmondal.portfolio.models.ecommerce.Category;
import com.rahulmondal.portfolio.models.ecommerce.Product;

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

    // ECOMMERCE PAGE 

    public ProductResponseDTO toProductResponseDto(Product entity){
        return ProductResponseDTO.builder()
        .description(entity.getDescription())
        .discount(entity.getDiscount())
        .id(entity.getId())
        .image(entity.getImage())
        .isFeatured(entity.isFeatured())
        .name(entity.getName())
        .price(entity.getPrice())
        .rating(entity.getRating())
        .categoryName(entity.getCategory().getName())
        .build();
    }
    
    public CategoryResponseDTO toCategoryResponseDTO(Category entity){
        return CategoryResponseDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .build();
    }
}
