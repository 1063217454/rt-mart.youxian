package com.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：用户信息表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 20:41:54
 */
@Entity
@Data
public class CustomerInf {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer customerInfId;//自增主键
    private Integer customerId;//customer_login表的自增ID
    private String customerName;//用户真实姓名
    private boolean identityCardType;//证件类型：1 身份证，2 军官证，3 护照
    private String identityCardNo;//证件号码
    private Integer mobilePhone;//手机号
    private String customerEmail;//邮箱
    private String gender;//性别
    private int userPoint;//用户积分
    private Date registerTime;//注册时间
    private Date birthday;//会员生日
    private Integer customerLevel;//会员级别：1 普通会员，2 青铜，3白银，4黄金，5钻石
    private BigDecimal userMoney;//用户余额
    private Date modifiedTime;//最后修改时间

}