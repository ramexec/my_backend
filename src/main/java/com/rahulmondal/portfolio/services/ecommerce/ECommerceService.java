package com.rahulmondal.portfolio.services.ecommerce;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rahulmondal.portfolio.dto.DTOmapper;
import com.rahulmondal.portfolio.dto.requests.ecommerce.CreateCategoryRequestDTO;
import com.rahulmondal.portfolio.dto.requests.ecommerce.CreateProductRequestDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.ProductResponseDTO;
import com.rahulmondal.portfolio.models.ecommerce.Category;
import com.rahulmondal.portfolio.models.ecommerce.Product;
import com.rahulmondal.portfolio.repository.ecommerce.CategoryRepository;
import com.rahulmondal.portfolio.repository.ecommerce.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ECommerceService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DTOmapper dtoMapper;

    public List<ProductResponseDTO> getAllProducts(){
        try{
           return productRepository.findAll().stream().map(dtoMapper::toProductResponseDto).collect(Collectors.toList());
        }
        catch(Exception e){
            throw e;
        }
    }

    public Page<ProductResponseDTO> getAllProductsPaginated(int page , int size , String query){

        if(size <= 0)
        {
            size = 1 ; 
        }

        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Product> products= null;

        if(query != null && !query.isBlank()){
            products = productRepository.findByNameContainingIgnoreCase(query,pageRequest);
        }else{
            products = productRepository.findAll(pageRequest);
        }

        return products.map(dtoMapper::toProductResponseDto);
    }

    public List<ProductResponseDTO> getAllFeatured() {
        try{
            return productRepository.findByisFeaturedTrue().stream().map(dtoMapper::toProductResponseDto).collect(Collectors.toList());
        }catch(Exception e )
        {
            throw e;
        }
    }

    public ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO) {
        Product product = new Product();
        Category category = categoryRepository.findById(createProductRequestDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Not Found"));
        
        try{
            product.setCategory(category);
            product.setDescription(createProductRequestDTO.getDescription());
            product.setDiscount(createProductRequestDTO.getDiscount());
            product.setFeatured(createProductRequestDTO.isFeatured());
            product.setImage(createProductRequestDTO.getImage());
            product.setName(createProductRequestDTO.getName());
            product.setPrice(createProductRequestDTO.getPrice());
            product.setRating(createProductRequestDTO.getRating());
            productRepository.save(product);
            return dtoMapper.toProductResponseDto(product);
        }
        catch(Exception e)
        {
            throw e;
        }

    }

    public String createCategory(CreateCategoryRequestDTO createCategoryRequestDTO) {
        try{
            Category category = new Category();
            category.setName(createCategoryRequestDTO.getName());
            categoryRepository.save(category);

            return category.getName();
        }catch(Exception e){
            throw e;
        }

    }
}
