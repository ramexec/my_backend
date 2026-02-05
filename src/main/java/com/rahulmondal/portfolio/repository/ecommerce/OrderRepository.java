package com.rahulmondal.portfolio.repository.ecommerce;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rahulmondal.portfolio.models.ecommerce.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("""
            SELECT o
            FROM ecommerce_orders o
            JOIN o.cart c
            JOIN c.user u
            WHERE u.id = :userId
                   """)
    Page<Order> findOrdersByUserId(@Param("userId") long id, Pageable pageRequest);

    @Query("""
                SELECT o
                FROM ecommerce_orders o
                ORDER BY
                  CASE o.status
                    WHEN 'PENDING' THEN 1
                    WHEN 'COMPLETED' THEN 2
                    WHEN 'CANCELLED' THEN 3
                    ELSE 4
                  END,
                  o.createdAt DESC
            """)
    Page<Order> findAllOrderedByStatusAndCreatedAt(Pageable pageable);

}
