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
 * 备注(数据表的comment字段)：物流公司信息表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:17:18
 */
@Entity
@Data
public class ShippingInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer shipId;//主键ID
    private String shipName;//物流公司名称
    private String shipContact;//物流公司联系人
    private String telephone;//物流公司联系电话
    private BigDecimal price;//配送价格
    private Date modifiedTime;//最后修改时间

}