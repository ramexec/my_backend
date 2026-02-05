package com.rahulmondal.portfolio.services.ecommerce;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rahulmondal.portfolio.configs.CustomUserDetails;
import com.rahulmondal.portfolio.dto.DTOmapper;
import com.rahulmondal.portfolio.dto.requests.ecommerce.AddToCartRequestDTO;
import com.rahulmondal.portfolio.dto.requests.ecommerce.CreateCategoryRequestDTO;
import com.rahulmondal.portfolio.dto.requests.ecommerce.CreateProductRequestDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.CartItemResponseDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.CategoryResponseDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.OrdersResponseDTO;
import com.rahulmondal.portfolio.dto.response.ecommerce.ProductResponseDTO;
import com.rahulmondal.portfolio.error.ecommerce.CartNotFoundException;
import com.rahulmondal.portfolio.models.User;
import com.rahulmondal.portfolio.models.ecommerce.Cart;
import com.rahulmondal.portfolio.models.ecommerce.CartItem;
import com.rahulmondal.portfolio.models.ecommerce.Category;
import com.rahulmondal.portfolio.models.ecommerce.Order;
import com.rahulmondal.portfolio.models.ecommerce.OrderStatus;
import com.rahulmondal.portfolio.models.ecommerce.Product;
import com.rahulmondal.portfolio.repository.ecommerce.CartItemRepository;
import com.rahulmondal.portfolio.repository.ecommerce.CartRepository;
import com.rahulmondal.portfolio.repository.ecommerce.CategoryRepository;
import com.rahulmondal.portfolio.repository.ecommerce.OrderRepository;
import com.rahulmondal.portfolio.repository.ecommerce.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ECommerceService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final DTOmapper dtoMapper;

    public List<ProductResponseDTO> getAllProducts() {
        try {
            return productRepository.findAll().stream().map(dtoMapper::toProductResponseDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    public Page<ProductResponseDTO> getAllProductsPaginated(int page, int size, String query) {

        if (size <= 0) {
            size = 1;
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products = null;

        if (query != null && !query.isBlank()) {
            products = productRepository.findByNameContainingIgnoreCase(query, pageRequest);
        } else {
            products = productRepository.findAll(pageRequest);
        }

        return products.map(dtoMapper::toProductResponseDto);
    }

    public List<ProductResponseDTO> getAllFeatured() {
        try {
            return productRepository.findByisFeaturedTrue().stream().map(dtoMapper::toProductResponseDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    public ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO) {
        Product product = new Product();
        Category category = categoryRepository.findById(createProductRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Not Found"));

        try {
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
        } catch (Exception e) {
            throw e;
        }

    }

    public String createCategory(CreateCategoryRequestDTO createCategoryRequestDTO) {
        try {
            Category category = new Category();
            category.setName(createCategoryRequestDTO.getName());
            categoryRepository.save(category);

            return category.getName();
        } catch (Exception e) {
            throw e;
        }

    }

    public List<CategoryResponseDTO> getCategories() {

        try {
            return categoryRepository.findAll().stream().map(dtoMapper::toCategoryResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean deleteProduct(long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean updateCategory(Long id, CreateCategoryRequestDTO entity) {

        try {
            Category cat = categoryRepository.findById(id).orElseThrow();
            cat.setName(entity.getName());
            categoryRepository.save(cat);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean deleteCategory(Long id) {
        try {
            Category cat = categoryRepository.findById(id).orElseThrow();
            categoryRepository.delete(cat);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean updateProduct(Long id, CreateProductRequestDTO entity) {
        try {
            Category cat = categoryRepository.findById(entity.getCategoryId()).orElseThrow();
            Product product = productRepository.findById(id).orElseThrow();
            product.setCategory(cat);
            product.setDescription(entity.getDescription());
            product.setDiscount(entity.getDiscount());
            product.setFeatured(entity.isFeatured());
            product.setImage(entity.getImage());
            product.setName(entity.getName());
            product.setPrice(entity.getPrice());
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public Boolean addToCart(AddToCartRequestDTO addToCartRequestDTO) {
        try {
            User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getUser();
            Optional<Cart> optionalCart = cartRepository.findByUserIdAndIsCurrentCart(user.getId(), true);

            Cart cart;
            if (optionalCart.isPresent()) {
                cart = optionalCart.get();
            } else {
                cart = new Cart();
                cart.setUser(user);
                cart.setCurrentCart(true);
                cart = cartRepository.save(cart);
            }

            Product product = productRepository.findById(addToCartRequestDTO.getProductId()).get();
            Optional<CartItem> optionalItem;
            optionalItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

            CartItem cartItem;
            if (optionalItem.isPresent()) {
                cartItem = optionalItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + addToCartRequestDTO.getQuantity());
            } else {
                cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setQuantity(addToCartRequestDTO.getQuantity());
                cartItem = cartItemRepository.save(cartItem);
            }

            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CartItemResponseDTO> getPersonalCartItems() {
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser();

        Cart cart = cartRepository.findByUserIdAndIsCurrentCart(user.getId(), true)
                .orElseThrow(() -> new CartNotFoundException("No Cart Registerd Yet !"));

        return cart.getCartItems().stream().map(dtoMapper::toCartItemsResponseDTO).collect(Collectors.toList());
    }

    public Boolean deleteItemFromCurrentCart(UUID id) {
        try {
            User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getUser();
            Cart cart = cartRepository.findByUserIdAndIsCurrentCart(user.getId(), true).get();
            CartItem item = cartItemRepository.findByIdAndCartId(id, cart.getId()).get();
            cartItemRepository.delete(item);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public Boolean checkoutCurrentCart() {
        try {
            User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getUser();
            Cart cart = cartRepository.findByUserIdAndIsCurrentCart(user.getId(), true)
                    .orElseThrow(() -> new CartNotFoundException("No Cart Exists Yet"));

            List<CartItem> items = cart.getCartItems();

            double totalCost = 0.0;
            Order order = new Order();

            for (CartItem item : items) {
                totalCost += (item.getProduct().getPrice()
                        - (item.getProduct().getPrice() * 0.01 * item.getProduct().getDiscount())) * item.getQuantity();
            }

            order.setCart(cart);
            order.setCreatedAt(LocalDateTime.now());
            order.setStatus(OrderStatus.PENDING);
            order.setTotalCost(totalCost);

            orderRepository.save(order);
            cart.setCurrentCart(false);
            cartRepository.save(cart);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public Page<OrdersResponseDTO> getAllOrdersPaged(int page, int size) {

        if (size <= 0) {
            size = 1;
        }
        try {
            User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getUser();

            PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());
            Page<Order> order = null;

            order = orderRepository.findOrdersByUserId(user.getId(), pageRequest);

            return order.map(dtoMapper::toOrderResponse);

        } catch (Exception e) {
            throw e;
        }
    }

    public Page<OrdersResponseDTO> getAllOrdersPagedAdmin(int page, int size) {
        if (size <= 0) {
            size = 1;
        }
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Order> order = null;

            order = orderRepository.findAllOrderedByStatusAndCreatedAt(pageRequest);

            return order.map(dtoMapper::toOrderResponse);

        } catch (Exception e) {
            throw e;
        }
    }
}
