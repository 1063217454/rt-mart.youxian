package com.customer.server.impl;

import com.customer.model.CustomerInf;
import com.customer.model.CustomerLogin;
import com.customer.repository.CustomerInfRepository;
import com.customer.repository.CustomerLoginRepository;
import com.customer.server.CustomerService;
import com.customer.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerLoginRepository customerLoginRepository;

    @Autowired
    CustomerInfRepository customerInfRepository;

    @Override
    @Transactional
    public String register(CustomerLogin customerLogin) {
        //默认customerLogin.getLoginName()不为空
        /**
         * 1,验证该账户有没有被注册过
         */
        //System.out.println("loginName="+customerLogin.getLoginName());
        List<CustomerLogin> customerLogins = customerLoginRepository.findByLoginName(customerLogin.getLoginName());
        System.out.println("size="+customerLogins.size());
        if(customerLogins.size()==0){
            /**
             * 2,没有被注册过，就注册（往login和inf表新增数据）
             */
            String oldpwd = customerLogin.getPassword();
            String newpwd = MD5.PwdToMd5(oldpwd);
            customerLogin.setPassword(newpwd);
            //Date date = new Date();
            customerLogin.setModifiedTime(new Date());
            customerLogin.setUserStats(1);
             customerLoginRepository.save(customerLogin);
            System.out.println("customerLogin注册成功！");
            //看login表是否注册成功
            List<CustomerLogin> customerLogins1 = customerLoginRepository.findByLoginName(customerLogin.getLoginName());
            if(customerLogins1.size()>0){
                CustomerInf customerInf = new CustomerInf();
                customerInf.setCustomerId(customerLogins1.get(0).getCustomerId());
                customerInf.setUserPoint(0);
                customerInf.setRegisterTime(customerLogins1.get(0).getModifiedTime());
                customerInf.setCustomerLevel(1);
                customerInf.setModifiedTime(customerLogins1.get(0).getModifiedTime());
                customerInf.setUserMoney(new BigDecimal("0.00"));
                customerInfRepository.save(customerInf);
                System.out.println("customerInf注册成功！");

                return "0000";
            }else{
                System.out.println("login表没有相关数据！");
                return "0002";
            }
        }else{
            /**
             * 3,已被注册过，返回提示信息和注册失败码
             */
            System.out.println("该账号已被注册！");
            return "0001";
        }
    }

    @Override
    public String modifyCustomerName(CustomerInf customerInf) {
        int num = customerInfRepository.updateCustomerNameByCustomerId(customerInf.getCustomerName(),customerInf.getCustomerId());
        System.out.println("num="+ num);
        if(num>0){//修改成功
            return "0000";
        }else{//修改失败
            return "0001";
        }
    }

    @Override
    @Transactional
    public String modifyPassword(CustomerLogin customerLogin, String newpwd) {
        String oldpwd = customerLogin.getPassword();
        String oldPwd2MD5 = MD5.PwdToMd5(oldpwd);
        String newPwd2MD5 = MD5.PwdToMd5(newpwd);
        List<CustomerLogin> customerLogins = customerLoginRepository.findByCustomerIdAndPassword(customerLogin.getCustomerId(),oldPwd2MD5);
        if(customerLogins != null && customerLogins.size()>0){//旧密码正确
            int num = customerLoginRepository.updatePasswordByCustomerId(newPwd2MD5,customerLogin.getCustomerId());
            if(num>0){//修改成功
                return "0000";
            }else{//修改失败
                return "0001";
            }
        }else{//旧密码错误
            return "0002";
        }
    }

    @Override
    @Transactional
    public  Map<String,CustomerLogin> login(CustomerLogin customerLogin) {
        Map<String,CustomerLogin> map = new HashMap<String,CustomerLogin>();
        String oldpwd = customerLogin.getPassword();
        String newpwd = MD5.PwdToMd5(oldpwd);
        //1、先判断用户名是否存在
        List<CustomerLogin> customerLogins1 = customerLoginRepository.findByLoginName(customerLogin.getLoginName());
        if(customerLogins1 !=null && customerLogins1.size()>0){//用户名正常
            //2、再判断密码是否正确
            List<CustomerLogin> customerLogins2 = customerLoginRepository.findByLoginNameAndPassword(customerLogin.getLoginName(),newpwd);
            if(customerLogins2 !=null && customerLogins2.size()>0){//登录成功
                map.put("0000",customerLogins2.get(0));
            }else{//密码错误，登录失败
                map.put("0002",null);
            }
        }else{//没有该用户名，登录失败
                map.put("0001",null);
        }
        return map;
    }
}
