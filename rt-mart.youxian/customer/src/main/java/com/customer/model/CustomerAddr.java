package com.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：用户地址表(收货地址)
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 20:41:54
 */

@Entity
@Data
public class CustomerAddr {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer customerAddrId;//自增主键ID
    private Integer customerId;//customer_login表的自增ID
    private String zip;//邮编
    private String province;//地区表中省份的ID
    private String city;//地区表中城市的ID
    private String district;//地区表中的区ID
    private String street;//地区表中的街道ID
    private String address;//具体的地址门牌号
    private Integer isDefault;//是否默认地址 0：不是 1：是
    private Date modifiedTime;//最后修改时间

}