package com.order.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：购物车表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:17:18
 */
@Entity
@Data
public class OrderCart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer cartId;//购物车ID
    private  Integer customerId;//用户ID
    private Integer productId;//商品ID
    private int productAmount;//加入购物车商品数量
    private BigDecimal price;//商品价格
    private Date addTime;//加入购物车时间
    private Date modifiedTime;//最后修改时间

}