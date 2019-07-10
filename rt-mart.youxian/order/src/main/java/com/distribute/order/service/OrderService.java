package com.distribute.order.service;

import java.math.BigDecimal;
import java.util.Map;

public interface OrderService {

    Map<String,Object>  createOrder(Integer customerId, String orderinfo, BigDecimal totalPrice,String address,String shippingUser);

    Map<String,Object> pay(Integer customerId,String orderSn,Integer payType);

    Map<String,Object> findOrderListByStatus(Integer customerId,Integer status,Integer page,Integer count);

    Map<String,Object> deleteOrder(Integer customerId,String orderSn);

    Map<String,Object> confirmReceipt(Integer customerId,String orderSn);

    Map<String,Object> findShoppingCart(Integer customerId);

    Map<String,Object> findOrderInfo(Integer customerId,String orderSn);

    Map<String,Object> syncShoppingCart(Integer customerId,String data);
}
