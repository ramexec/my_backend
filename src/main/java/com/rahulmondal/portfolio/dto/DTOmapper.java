package com.rahulmondal.portfolio.dto;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rahulmondal.portfolio.dto.response.ServicesProvidedDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.CartItemResponseDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.CategoryResponseDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.OrdersResponseDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.ProductResponseDTO;
import com.rahulmondal.portfolio.models.ServicesProvided;
import com.rahulmondal.portfolio.models.ecommerce.CartItem;
import com.rahulmondal.portfolio.models.ecommerce.Category;
import com.rahulmondal.portfolio.models.ecommerce.Order;
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
        .categoryId(entity.getCategory().getId())
        .build();
    }
    
    public CategoryResponseDTO toCategoryResponseDTO(Category entity){
        return CategoryResponseDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .build();
    }

    public CartItemResponseDTO toCartItemsResponseDTO(CartItem entity){
        return CartItemResponseDTO.builder()
        .name(entity.getId())
        .quantity(entity.getQuantity())
        .product(this.toProductResponseDto(entity.getProduct()))
        .build();
    }

    public OrdersResponseDTO toOrderResponse(Order entity){
        return OrdersResponseDTO.builder()
                .cartItems(entity.getCart().getCartItems().stream().map(this::toCartItemsResponseDTO).collect(Collectors.toList()))
                .createdAt(entity.getCreatedAt())
                .completedAt(entity.getCompletedAt())
                .status(entity.getStatus().toString())
                .totalCost(entity.getTotalCost())
                .id(entity.getId())
                .build();
    }
}
