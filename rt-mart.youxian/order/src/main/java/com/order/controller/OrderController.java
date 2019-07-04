package com.order.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 1、创建订单
     */
    @PostMapping("/createOrder")
    public void createOrder(){

    }

    /**
     * 2、支付
     */
    @PostMapping("/pay")
    public void pay(){

    }

    /**
     * 3、根据订单状态查询订单信息
     */
    @GetMapping("/findOrderListByStatus")
    public void findOrderListByStatus(){

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
