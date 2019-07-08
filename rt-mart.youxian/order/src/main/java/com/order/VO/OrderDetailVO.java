package com.order.VO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailVO {

    private Integer orderDetailId;

    private Integer productId;

    private String productName;

    private Integer productCnt;

    private String productPic;

    private BigDecimal productPrice;

    private Integer commentStatus;
}
