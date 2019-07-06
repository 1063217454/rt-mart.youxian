package com.product.VO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommodityVo {
    private Integer productId;//商品ID

    private String productName;//商品名称

    private String masterPic;//主图Url

    private BigDecimal price;//价格

    private int sales;//销量
}
