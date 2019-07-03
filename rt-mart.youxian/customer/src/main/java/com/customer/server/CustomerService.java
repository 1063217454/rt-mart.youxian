package com.customer.server;

import com.customer.VO.CustomerAddrAndInfVO;
import com.customer.VO.CustomerLoginAndInfVO;
import com.customer.model.CustomerInf;
import com.customer.model.CustomerLogin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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

    //修改用户信息表的用户头像URL（customerInf）
    String modifyHeadPic(CustomerInf customerInf);

    //根据用户ID查询用户信息
    CustomerLoginAndInfVO getCustomerById(Integer customerId);

    //新增收货地址
    String addReceiveAddress(Integer customerId,String customerName,String mobilePhone,String address,String zip);

    //收货地址列表
    List<CustomerAddrAndInfVO> receiveAddressList(Integer customerId);

    //设置默认收货地址
    String setDefaultReceiveAddress(Integer customerId,Integer customerAddrId);

    //修改收货信息
    String changeReceiveAddress(Integer customerId,Integer customerAddrId,String customerName,String mobilePhone,String address,String zip);


}
