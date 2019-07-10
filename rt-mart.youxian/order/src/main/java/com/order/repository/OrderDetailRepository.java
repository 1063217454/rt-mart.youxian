package com.order.repository;

import com.order.model.OrderCart;
import com.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    List<OrderDetail> findByOrderDetailId(Integer orderDetailId);

    List<OrderDetail> findByOrderId(Integer orderId);

    @Modifying
    @Transactional
    @Query("delete from OrderDetail od where od.orderId=:orderId")
    void deleteByOrderId(@Param("orderId")Integer orderId);
}
