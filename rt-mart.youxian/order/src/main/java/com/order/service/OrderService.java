package com.order.service;

import java.math.BigDecimal;
import java.util.Map;

public interface OrderService {

    Map<String,Object>  createOrder(Integer customerId, String orderinfo, BigDecimal totalPrice,String address,String shippingUser);

    Map<String,Object> pay(Integer customerId,String orderSn,Integer payType);

    Map<String,Object> findOrderListByStatus(Integer customerId,Integer status,Integer page,Integer count);
}
