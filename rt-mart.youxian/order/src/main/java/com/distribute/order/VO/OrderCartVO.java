package com.distribute.order.VO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCartVO {
    private Integer productId;//商品ID
    private String productName;//商品名称
    private String pic;//商品缩略图url
    private BigDecimal price;//商品价格
    private int productAmount;//加入购物车商品数量
}
