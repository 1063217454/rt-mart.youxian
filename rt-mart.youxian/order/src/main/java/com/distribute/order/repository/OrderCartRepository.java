package com.distribute.order.repository;

import com.distribute.order.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderCartRepository extends JpaRepository<OrderCart,Integer> {
    List<OrderCart> findByCartId(Integer cartId);

    List<OrderCart> findByCustomerId(Integer customerId);

    OrderCart findByCustomerIdAndProductId(Integer customerId,Integer productId);
}
