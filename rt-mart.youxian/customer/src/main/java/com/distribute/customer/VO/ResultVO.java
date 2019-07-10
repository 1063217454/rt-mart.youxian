package com.distribute.customer.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 */
@Data
public class ResultVO<T> {
    //错误码
    private String status;

    //提示信息
    private String message;

    //具体内容 暂时不要
    //private T data;
}
