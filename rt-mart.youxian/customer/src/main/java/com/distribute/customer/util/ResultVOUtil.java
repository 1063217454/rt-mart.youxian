package com.distribute.customer.util;

import com.distribute.customer.VO.ResultVO;

public class ResultVOUtil {
    ////////////////////////////////Register///////////////////////////
    public static ResultVO successToRegister(Object object) {
        ResultVO resultVO = new ResultVO();
       // resultVO.setData(object);这个暂时不要
        resultVO.setStatus("0000");
        resultVO.setMessage("注册成功");
        return resultVO;
    }

    public static ResultVO error1ToRegister(Object object) {
        ResultVO resultVO = new ResultVO();
        // resultVO.setData(object);这个暂时不要
        resultVO.setStatus("0001");
        resultVO.setMessage("该账号已注册过");
        return resultVO;
    }

    public static ResultVO error2ToRegister(Object object) {
        ResultVO resultVO = new ResultVO();
        // resultVO.setData(object);这个暂时不要
        resultVO.setStatus("0002");
        resultVO.setMessage("系统异常");
        return resultVO;
    }
    ////////////////////////////////ModifyCustomerName///////////////////////////
    public static ResultVO successToModifyCustomerName(Object object) {
        ResultVO resultVO = new ResultVO();
        // resultVO.setData(object);这个暂时不要
        resultVO.setStatus("0000");
        resultVO.setMessage("修改成功");
        return resultVO;
    }

    public static ResultVO error1ToModifyCustomerName(Object object) {
        ResultVO resultVO = new ResultVO();
        // resultVO.setData(object);这个暂时不要
        resultVO.setStatus("0001");
        resultVO.setMessage("修改失败");
        return resultVO;
    }

    ////////////////////////////////ModifyPassword///////////////////////////
    public static ResultVO successToModifyPassword(Object object) {
        ResultVO resultVO = new ResultVO();
        // resultVO.setData(object);这个暂时不要
        resultVO.setStatus("0000");
        resultVO.setMessage("修改成功");
        return resultVO;
    }

    public static ResultVO error1ToModifyPassword(Object object) {
        ResultVO resultVO = new ResultVO();
        // resultVO.setData(object);这个暂时不要
        resultVO.setStatus("0001");
        resultVO.setMessage("修改失败");
        return resultVO;
    }

    public static ResultVO error2ToModifyPassword(Object object) {
        ResultVO resultVO = new ResultVO();
        // resultVO.setData(object);这个暂时不要
        resultVO.setStatus("0002");
        resultVO.setMessage("修改失败，旧密码错误");
        return resultVO;
    }
}