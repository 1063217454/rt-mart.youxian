package com.customer.util;

import com.customer.VO.CustomerLoginResultVO;

public class CustomerLoginVOUtil {
    public static CustomerLoginResultVO success(Object object) {
        CustomerLoginResultVO customerLoginResultVO = new CustomerLoginResultVO();
        customerLoginResultVO.setResult(object);
        customerLoginResultVO.setStatus("0000");
        customerLoginResultVO.setMessage("登录成功");
        return customerLoginResultVO;
    }

    public static CustomerLoginResultVO error1(Object object) {
        CustomerLoginResultVO customerLoginResultVO = new CustomerLoginResultVO();
        customerLoginResultVO.setResult(object);
        customerLoginResultVO.setStatus("0001");
        customerLoginResultVO.setMessage("登录失败，用户名错误");
        return customerLoginResultVO;
    }

    public static CustomerLoginResultVO error2(Object object) {
        CustomerLoginResultVO customerLoginResultVO = new CustomerLoginResultVO();
        customerLoginResultVO.setResult(object);
        customerLoginResultVO.setStatus("0002");
        customerLoginResultVO.setMessage("登录失败，密码错误");
        return customerLoginResultVO;
    }
}