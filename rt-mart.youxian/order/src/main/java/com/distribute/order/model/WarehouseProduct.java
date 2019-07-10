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
 * 备注(数据表的comment字段)：商品库存表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:17:18
 */
@Entity
@Data
public class WarehouseProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer wpId;//商品库存ID
    private Integer productId;//商品ID
    private Integer wId;//仓库ID
    private Integer currentCnt;//当前商品数量
    private Integer lockCnt;//当前占用数据
    private Integer inTransitCnt;//在途数据
    private BigDecimal averageCost;//移动加权成本
    private Date modifiedTime;//最后修改时间

}