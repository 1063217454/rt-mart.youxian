package com.customer.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 */
@Data
public class CustomerLoginResultVO<T> {
    //login信息
    private T result;

    //错误码
    private String status;

    //提示信息
    private String message;


}
