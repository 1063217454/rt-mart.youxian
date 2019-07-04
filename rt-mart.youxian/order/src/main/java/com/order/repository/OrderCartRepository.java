package com.order.repository;

import com.order.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderCartRepository extends JpaRepository<OrderCart,Integer> {
    List<OrderCart> findByCartId(Integer cartId);
}
