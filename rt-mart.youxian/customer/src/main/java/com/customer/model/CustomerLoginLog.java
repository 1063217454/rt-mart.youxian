package com.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：用户登陆日志表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 20:41:54
 */
@Entity
@Data
public class CustomerLoginLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer loginId;//登录日志ID
    private Integer customerId;//登录用户ID
    private Date loginTime;//用户登陆时间
    private String loginIp;//登录IP
    private String loginIpAddr;//登录Ip的地址
    private Integer loginType;//登陆类型：0未成功，1成功

}