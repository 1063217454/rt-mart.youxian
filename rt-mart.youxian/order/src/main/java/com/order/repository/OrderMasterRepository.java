package com.order.repository;

import com.order.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,Integer> {
    List<OrderMaster> findByOrderId(Integer orderId);

    OrderMaster findByOrderSn(String orderSn);

    //支付
    @Modifying
    @Transactional
    @Query("update OrderMaster om set om.paymentMethod = ?1 ,om.orderStatus = 2 where om.orderStatus=1 and om.orderSn = ?2")
    int updateOrderMasterByorderSn1(Integer payType,String orderSn);

    //收货
    @Modifying
    @Transactional
    @Query("update OrderMaster om set om.orderStatus = 3 where om.orderStatus=2 and om.orderSn = ?1")
    int updateOrderMasterByorderSn2(String orderSn);

    @Query(value = "select om from OrderMaster om where om.customerId=:customerId and om.orderStatus=:orderStatus")
    Page<OrderMaster> findByCustomerIdAndOrderStatusPageable(@Param("customerId") Integer customerId,
                                                             @Param("orderStatus") Integer orderStatus,
                                                             Pageable pageable);

    @Query(value = "select om from OrderMaster om where om.customerId=:customerId")
    Page<OrderMaster> findByCustomerIdPageable(@Param("customerId") Integer customerId,Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from OrderMaster om where om.orderSn=:os")
    void deleteByOrderSn(@Param("os")String orderSn);

}
