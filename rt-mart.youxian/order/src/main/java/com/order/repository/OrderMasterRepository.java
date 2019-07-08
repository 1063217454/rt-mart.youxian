package com.order.repository;

import com.order.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,Integer> {
    List<OrderMaster> findByOrderId(Integer orderId);

    OrderMaster findByOrderSn(String orderSn);

    @Modifying
    @Transactional
    @Query("update OrderMaster om set om.paymentMethod = ?1 ,om.orderStatus = 2 where om.orderSn = ?2")
    int updateOrderMasterByorderSn(Integer payType,String orderSn);

    @Query(value = "select om from OrderMaster om where om.customerId=:customerId and om.orderStatus=:orderStatus")
    Page<OrderMaster> findByCustomerIdAndOrderStatusPageable(@Param("customerId") Integer customerId,
                                                             @Param("orderStatus") Integer orderStatus,
                                                             Pageable pageable);

    @Query(value = "select om from OrderMaster om where om.customerId=:customerId")
    Page<OrderMaster> findByCustomerIdPageable(@Param("customerId") Integer customerId,Pageable pageable);
}
