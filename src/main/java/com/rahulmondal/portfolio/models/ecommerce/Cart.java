package com.rahulmondal.portfolio.models.ecommerce;

import java.util.List;
import java.util.UUID;

import com.rahulmondal.portfolio.models.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private boolean isCurrentCart;

    @OneToOne(mappedBy = "cart")
    private Order order;
}
