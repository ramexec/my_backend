package com.rahulmondal.portfolio.controller.ecommerce;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulmondal.portfolio.dto.response.ecommerce.ProductResponseDTO;
import com.rahulmondal.portfolio.services.ecommerce.ECommerceService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/ecommerce/openapi")
@RestController
@RequiredArgsConstructor
public class EcommerceOpenApiController {

    private final ECommerceService eCommerceService;
    
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(eCommerceService.getAllProducts());
    }

    @GetMapping("/products/featured")
    public ResponseEntity<List<ProductResponseDTO>> getAllFeatured() {
        return ResponseEntity.ok(eCommerceService.getAllFeatured());
    }

}
