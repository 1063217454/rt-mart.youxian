package com.order.controller;

import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1、创建订单
     */
    @PostMapping("/createOrder")
    public Map<String,Object> createOrder(@RequestHeader(name = "customerId")Integer customerId,
                              @RequestParam(name = "orderInfo")String orderinfo,
                              @RequestParam(name = "totalPrice")BigDecimal totalPrice,
                              @RequestParam(name = "address")String address,
                              @RequestParam("shippingUser")String shippingUser){
        Map<String,Object> map = orderService.createOrder(customerId,orderinfo,totalPrice,address,shippingUser);
        return map;
    }

    /**
     * 2、支付
     */
    @PostMapping("/pay")
    public Map<String,Object> pay(@RequestHeader(name = "customerId")Integer customerId,
                                  @RequestParam(name = "orderSn")String orderSn,
                                  @RequestParam(name = "payType")Integer payType){

        Map<String,Object> map = orderService.pay(customerId,orderSn,payType);
        return map;
    }

    /**
     * 3、根据订单状态查询订单信息
     * status ++++++++  0=查看全部 1=查看待付款 2=查看待收货 3=查看待评价 9=查看已完成
     */
    @GetMapping("/findOrderListByStatus")
    public Map<String,Object> findOrderListByStatus(@RequestHeader("customerId")Integer customerId,
                                                    @RequestParam(name = "status")Integer status,
                                                    @RequestParam(name = "page")Integer page,
                                                    @RequestParam(name = "count")Integer count){
        Map<String,Object> map = orderService.findOrderListByStatus(customerId,status,page,count);
        return map;
    }

    /**
     * 4、删除订单
     */
    @DeleteMapping("/deleteOrder")
    public void deleteOrder(){

    }

    /**
     * 5、收货
     */
    @PutMapping("/confirmReceipt")
    public void confirmReceipt(){

    }

    /**
     * 6、查询购物车
     */
    @GetMapping("/findShoppingCart")
    public void findShoppingCart(){

    }

    /**
     * 7、同步购物车数据
     */
    @PutMapping("/syncShoppingCart")
    public void syncShoppingCart(){

    }

    /**
     * 8、查询订单明细数据
     */
    @GetMapping("/findOrderInfo")
    public void findOrderInfo(){

    }

}
