package com.customer.server;

import com.customer.model.CustomerInf;
import com.customer.model.CustomerLogin;

import java.util.Map;

public interface CustomerService {
    //注册
    String register(CustomerLogin customerLogin);

    //登录
    Map<String,CustomerLogin> login(CustomerLogin customerLogin);

    //修改用户信息表的真实姓名（customerInf）
    String modifyCustomerName(CustomerInf customerInf);

    //修改用户密码（customerLogin）
    String modifyPassword(CustomerLogin customerLogin,String newpwd);

}
