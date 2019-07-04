package com.order.repository;

import com.order.model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,Integer> {
    List<OrderMaster> findByOrderId(Integer orderId);
}
