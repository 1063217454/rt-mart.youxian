package com.order.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderMasterVO {
    private String orderSn;

    private Integer customerId;

    private Integer paymentMethod;

    private BigDecimal paymentMoney;

    private String shippingCompName;

    private Date createTime;

    private String shippingSn;

    private Integer orderStatus;

    private List<OrderDetailVO> orderDetailVOS;

}
