package com.rahulmondal.portfolio.controller.ecommerce;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rahulmondal.portfolio.dto.requests.ecommerce.AddToCartRequestDTO;
import com.rahulmondal.portfolio.dto.requests.ecommerce.CreateCategoryRequestDTO;
import com.rahulmondal.portfolio.dto.requests.ecommerce.CreateProductRequestDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.CartItemsResponseDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.ProductResponseDTO;
import com.rahulmondal.portfolio.services.ecommerce.ECommerceService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/ecommerce")
@RestController
@RequiredArgsConstructor
public class ECommerceController {

    private final ECommerceService eCommerceService;

    // Products part 

    @PostMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        return ResponseEntity.ok(eCommerceService.createProduct(createProductRequestDTO));
    }
   
    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable long id){
        return ResponseEntity.ok(eCommerceService.deleteProduct(id));
    }

    @PutMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> updateProduct(@PathVariable Long id, @RequestBody CreateProductRequestDTO entity) {
        return ResponseEntity.ok(eCommerceService.updateProduct(id,entity));
    }
    

    //Category Part 

    @PostMapping("/category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
        return ResponseEntity.ok(eCommerceService.createCategory(createCategoryRequestDTO));
    }

    @PutMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> updateCategory(@PathVariable Long id, @RequestBody CreateCategoryRequestDTO entity) {
        return ResponseEntity.ok(eCommerceService.updateCategory(id,entity));
    }
    
    @DeleteMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok(eCommerceService.deleteCategory(id));
    }

    //CartPart 

    @PostMapping("/cart")
    public ResponseEntity<Boolean> addToCart(@RequestBody AddToCartRequestDTO addToCartRequestDTO) {
        return ResponseEntity.ok(eCommerceService.addToCart(addToCartRequestDTO));
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartItemsResponseDTO>> getPersonalCartItems() {
        return ResponseEntity.ok(eCommerceService.getPersonalCartItems());
    }
    
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Boolean> deleteCartItem(@PathVariable UUID id){
        return ResponseEntity.ok(eCommerceService.deleteItemFromCurrentCart(id));
    }
}

