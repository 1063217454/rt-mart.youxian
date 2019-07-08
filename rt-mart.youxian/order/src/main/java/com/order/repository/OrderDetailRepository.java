package com.order.repository;

import com.order.model.OrderCart;
import com.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    List<OrderDetail> findByOrderDetailId(Integer orderDetailId);

    List<OrderDetail> findByOrderId(Integer orderId);
}
