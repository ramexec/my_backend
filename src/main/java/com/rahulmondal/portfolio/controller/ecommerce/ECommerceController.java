package com.rahulmondal.portfolio.controller.ecommerce;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahulmondal.portfolio.dto.requests.ecommerce.CreateCategoryRequestDTO;
import com.rahulmondal.portfolio.dto.requests.ecommerce.CreateProductRequestDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.ProductResponseDTO;
import com.rahulmondal.portfolio.models.ecommerce.Product;
import com.rahulmondal.portfolio.services.ecommerce.ECommerceService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/ecommerce")
@RestController
@RequiredArgsConstructor
public class ECommerceController {

    private final ECommerceService eCommerceService;

    @PostMapping("/create/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        return ResponseEntity.ok(eCommerceService.createProduct(createProductRequestDTO));
    }
    
    @PostMapping("/create/category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
        return ResponseEntity.ok(eCommerceService.createCategory(createCategoryRequestDTO));
    }
}
