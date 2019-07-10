package com.distribute.order.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：订单详情表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:17:18
 */
@Entity
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer orderDetailId;//订单详情表ID
    private Integer orderId;//订单表ID
    private Integer productId;//商品表ID
    private String productName;//商品名称
    private Integer productCnt;//购买商品数量
    private BigDecimal productPrice;//购买商品单价
    private BigDecimal averageCost;//平均成本价格
    private Float weight;//商品重量
    private BigDecimal feeMoney;//优惠分摊金额
    private Integer wId;//仓库ID
    private Date modifiedTime;//最后修改时间

}