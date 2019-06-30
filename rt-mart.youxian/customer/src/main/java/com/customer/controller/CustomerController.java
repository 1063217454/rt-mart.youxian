package com.customer.controller;

import com.customer.VO.CustomerLoginResultVO;
import com.customer.VO.CustomerLoginVO;
import com.customer.VO.ResultVO;
import com.customer.model.CustomerInf;
import com.customer.model.CustomerLogin;
import com.customer.server.CustomerService;
import com.customer.util.CustomerLoginVOUtil;
import com.customer.util.ResultVOUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * 1、用户注册
     * @param phone
     * @param pwd
     * @return
     */
    @ApiOperation(value = "用户注册",notes = "用户注册")
    @ApiImplicitParams({@ApiImplicitParam(name="phone",value = "用户名(电话号码)",paramType = "path",dataType = "String"),
                        @ApiImplicitParam(name="pwd",value = "密码（MD5加密）",paramType = "path",dataType = "String")})
    @PostMapping("/register")
    public ResultVO register(@RequestParam(name = "phone") String phone,@RequestParam(name="pwd") String pwd){
        CustomerLogin customerLogin = new CustomerLogin();
        customerLogin.setLoginName(phone);
        customerLogin.setPassword(pwd);
       String code = customerService.register(customerLogin);
       if("0000".equals(code)){
           return ResultVOUtil.successToRegister(1);
       }else if("0001".equals(code)){
           return ResultVOUtil.error1ToRegister(1);
       }else{
           return ResultVOUtil.error2ToRegister(1);
       }
    }

    /**
     * 用户登录
     * @param phone
     * @param pwd
     * @return
     */
    @ApiOperation(value = "用户登录",notes = "用户登录")
    @ApiImplicitParams({@ApiImplicitParam(name="phone",value = "用户名(电话号码)",paramType = "path",dataType = "String"),
            @ApiImplicitParam(name="pwd",value = "密码（MD5加密）",paramType = "path",dataType = "String")})
    @PostMapping("/login")
    public CustomerLoginResultVO<CustomerLoginVO> login(@RequestParam(name = "phone") String phone,@RequestParam(name="pwd") String pwd){
        CustomerLogin customerLogin = new CustomerLogin();
        customerLogin.setLoginName(phone);
        customerLogin.setPassword(pwd);
        Map<String,CustomerLogin> map = customerService.login(customerLogin);
        String code = "";
        CustomerLogin customerLogin1 = new CustomerLogin();
        for (String key : map.keySet()) {
            code = key;
            customerLogin1 = map.get(key);
            System.out.println("Key = "+ key + ", Value = " +customerLogin1);
        }
        //构造数据
        CustomerLoginVO customerLoginVO = new CustomerLoginVO();
        if(customerLogin1 != null){
            customerLoginVO.setCustomerId(customerLogin1.getCustomerId());
            customerLoginVO.setLoginName(customerLogin1.getLoginName());
            customerLoginVO.setUserStats(customerLogin1.getUserStats());
        }
        if("0000".equals(code)){//登录成功
            return CustomerLoginVOUtil.success(customerLoginVO);
        }else if("0001".equals(code)){//用户名不存在
            return CustomerLoginVOUtil.error1(customerLoginVO);
        }else{//密码错误
            return CustomerLoginVOUtil.error2(customerLoginVO);
        }
    }

    /**
     * 修改用户真实姓名
     * @param customerId
     * @param customerName
     * @return
     */
    @ApiOperation(value = "修改用户真实姓名",notes = "修改用户真实姓名")
    @ApiImplicitParams({@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer"),
                        @ApiImplicitParam(name="customerName",value = "用户真实姓名",paramType = "path",dataType = "String")})
    @PutMapping("/modifyCustomerName")
    //@RequestHeader("customerId") Integer customerId
    public ResultVO modifyCustomerName(@RequestHeader("customerId") Integer customerId,@RequestParam(name = "customerName") String customerName){
        CustomerInf customerInf = new CustomerInf();
        customerInf.setCustomerId(customerId);
        customerInf.setCustomerName(customerName);
        String code = customerService.modifyCustomerName(customerInf);
        if("0000".equals(code)){
            return ResultVOUtil.successToModifyCustomerName(1);
        }else{
            return ResultVOUtil.error1ToModifyCustomerName(1);
        }
    }

    /**
     * 修改用户密码
     * @param customerId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @ApiOperation(value = "修改用户密码",notes = "修改用户密码")
    @ApiImplicitParams({@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer"),
                        @ApiImplicitParam(name="oldPwd",value = "旧用户密码",paramType = "path",dataType = "String"),
                        @ApiImplicitParam(name="newPwd",value = "新用户密码",paramType = "path",dataType = "String")})
    @PutMapping("/modifyPassword")
    public ResultVO modifyPassword(@RequestHeader("customerId") Integer customerId,
                                 @RequestParam(name = "oldPwd") String oldPwd,
                                 @RequestParam(name = "newPwd") String newPwd){
        CustomerLogin customerLogin = new CustomerLogin();
        customerLogin.setPassword(oldPwd);
        customerLogin.setCustomerId(customerId);
        String code = customerService.modifyPassword(customerLogin,newPwd);
        if("0000".equals(code)){
            return ResultVOUtil.successToModifyPassword(1);
        }else if("0001".equals(code)){
            return ResultVOUtil.error1ToModifyPassword(1);
        }else{
            return ResultVOUtil.error2ToModifyPassword(1);
        }
    }

    public String modifyHeadPic(){

        return null;
    }

    



}
