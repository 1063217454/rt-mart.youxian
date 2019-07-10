package com.distribute.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：用户登录表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 20:41:54
 */
@Entity
@Data
public class CustomerLogin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer customerId;//用户ID
    private String loginName;//用户登录名
    private String password;//md5加密的密码(暂时不用MD5加密，没时间搞)
    private Integer userStats;//用户状态
    private Date modifiedTime;//最后修改时间


}