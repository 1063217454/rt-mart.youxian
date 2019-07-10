package com.distribute.customer.VO;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerAddrAndInfVO {
    //收货地址ID
    private Integer customerAddrId;
    //用户ID
    private Integer customerId;
    //手机号
    private String mobilePhone;
    //真实姓名
    private String customerName;
    //是否是默认地址
    private Integer isDefault;
    //完整的收货地址
    private String address;
    //邮编
    private String zip;
    //最后修改时间
    private Date modifiedTime;
}
