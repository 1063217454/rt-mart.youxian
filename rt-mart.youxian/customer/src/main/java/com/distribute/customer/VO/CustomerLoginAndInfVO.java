package com.distribute.customer.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomerLoginAndInfVO {
    //ID
    private Integer customerId;
    //用户登录名
    private String loginName;
    //真名
    private String customerName;
    //手机号
    private String mobilePhone;
    //密码（MD5加密后的）
    private String password;
    //性别
    private String gender;
    //用户头像地址
    private String headPicUrl;
    //用户积分
    private Integer userPoint;
    //会员等级
    private Integer customerLevel;
    //用户余额
    private BigDecimal userMoney;
    //用户注册时间
    private Date registerTime;

}
