package com.customer.server.impl;

import com.customer.VO.CustomerAddrAndInfVO;
import com.customer.VO.CustomerLoginAndInfVO;
import com.customer.model.*;
import com.customer.repository.*;
import com.customer.server.CustomerService;
import com.customer.util.JsoupUtil;
import com.customer.util.MD5;
import com.customer.util.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerLoginRepository customerLoginRepository;

    @Autowired
    CustomerInfRepository customerInfRepository;

    @Autowired
    CustomerLoginLogRepository customerLoginLogRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    CustomerAddrRepository customerAddrRepository;

    @Autowired
    JsoupUtil jsoupUtil;

    /*@Autowired
    OSSClientUtil clientUtil;*/


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
                String ip = "";
                String addr = "";
                try {
                     ip = jsoupUtil.getIp();
                     addr = jsoupUtil.getAdress();
                }catch (Exception e){
                    e.printStackTrace();
                }
                CustomerLoginLog customerLoginLog = new CustomerLoginLog();
                customerLoginLog.setCustomerId(customerLogins2.get(0).getCustomerId());
                customerLoginLog.setLoginTime(new Date());
                customerLoginLog.setLoginType(1);//登陆类型：0未成功，1成功
                customerLoginLog.setLoginIp(ip);
                customerLoginLog.setLoginIpAddr(addr);

                customerLoginLogRepository.save(customerLoginLog);
                map.put("0000",customerLogins2.get(0));
            }else{//密码错误，登录失败
                map.put("0002",null);
            }
        }else{//没有该用户名，登录失败
                map.put("0001",null);
        }
        return map;
    }

    @Override
    public String modifyHeadPic(CustomerInf customerInf) {
        int num = customerInfRepository.updateHeadPicUrlByCustomerId(customerInf.getHeadPicUrl(),customerInf.getCustomerId());
        return num > 0 ? "0000":"0001";
    }

    @Override
    public CustomerLoginAndInfVO getCustomerById(Integer customerId) {
        CustomerLoginAndInfVO vo = new CustomerLoginAndInfVO();
        List<CustomerInf> customerInfs = customerInfRepository.findByCustomerId(customerId);
        List<CustomerLogin> customerLogins = customerLoginRepository.findByCustomerId(customerId);
        if(customerLogins!=null && customerLogins.size()>0 && customerInfs!=null && customerInfs.size()>0) {
            CustomerLogin customerLogin = customerLogins.get(0);
            CustomerInf customerInf = customerInfs.get(0);
            //customerLogin
            vo.setCustomerId(customerLogin.getCustomerId());
            vo.setLoginName(customerLogin.getLoginName());
            vo.setPassword(customerLogin.getPassword());
            //customerInf
            vo.setCustomerLevel(customerInf.getCustomerLevel());
            vo.setCustomerName(customerInf.getCustomerName());
            vo.setGender(customerInf.getGender());
            vo.setHeadPicUrl(customerInf.getHeadPicUrl());
            vo.setMobilePhone(customerInf.getMobilePhone());
            vo.setRegisterTime(customerInf.getRegisterTime());
            vo.setUserMoney(customerInf.getUserMoney());
            vo.setUserPoint(customerInf.getUserPoint());
            return vo;
        }else{
            return null;
        }
    }

    @Override
    public List<CustomerAddrAndInfVO> receiveAddressList(Integer customerId) {
        List<CustomerAddrAndInfVO> vos = new ArrayList<CustomerAddrAndInfVO>();
        List<CustomerInf> customerInfs = customerInfRepository.findByCustomerId(customerId);
        String customerName = customerInfs.get(0).getCustomerName();
        String mobilePhone = customerInfs.get(0).getMobilePhone();

        List<CustomerAddr> customerAddrs = customerAddrRepository.findByCustomerId(customerId);
        if(customerAddrs.size()>0){
            for(int i=0;i<customerAddrs.size();i++){
                CustomerAddrAndInfVO vo = new CustomerAddrAndInfVO();
                CustomerAddr customerAddr = customerAddrs.get(i);
                String address = areaRepository.findByCode(customerAddr.getProvince()).get(0).getName()+" "+
                                 areaRepository.findByCode(customerAddr.getCity()).get(0).getName()+" "+
                                 areaRepository.findByCode(customerAddr.getDistrict()).get(0).getName()+" "+
                                 areaRepository.findByCode(customerAddr.getStreet()).get(0).getName()+" "+
                                 customerAddr.getAddress();
                System.out.println("address="+address);
                vo.setAddress(address);
                vo.setZip(customerAddr.getZip());
                vo.setModifiedTime(customerAddr.getModifiedTime());
                vo.setIsDefault(customerAddr.getIsDefault());
                vo.setCustomerId(customerId);
                vo.setCustomerAddrId(customerAddr.getCustomerAddrId());
                vo.setCustomerName(customerName);
                vo.setMobilePhone(mobilePhone);
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public String setDefaultReceiveAddress(Integer customerId, Integer customerAddrId) {
        int num = customerAddrRepository.updateCustomerAddrByCustomerIdAndAddressId(customerId,customerAddrId);
        if(num>0){
            return "0000";
        }else{
            return "0001";
        }
    }

   @Override
    public String addReceiveAddress(Integer customerId, String customerName, String mobilePhone, String address, String zip) {
        String[] aa = address.split(" ");
        if(aa.length==5){
           CustomerAddr customerAddr = getCustomerAddr(customerId,address,zip);
            customerAddrRepository.save(customerAddr);
            //更新Inf表里的真实姓名和手机号码信息
            int num =customerInfRepository.updateCustomerNameAndMobilePhoneByCustomerId(customerName,mobilePhone,customerId);
            if(num>0){
                return "0000";//添加成功
            }else{
                return "0002";//更新Inf表里的真实姓名和手机号码信息异常
            }
        }else{
            return "0001";//收货地址信息异常
        }
    }

    public CustomerAddr getCustomerAddr(Integer customerId,String address,String zip){
        String[] aa = address.split(" ");
        String shenCode= "";
        String shiCode= "";
        String quCode= "";
        String jiedaoCode= "";
        //详细地址
        String addr = aa[4];
        //获取省的code
        String shen = aa[0];
        List<Area> areasShen = areaRepository.findByLevelAndNameLike(1,shen);
        shenCode = areasShen.get(0).getCode();
        //获取市的code
        String shi = aa[1];
        List<Area> areasShi = areaRepository.findByLevelAndNameLike(2,shi);
        if(areasShi.size()>=1){
            for(int i=0;i<areasShi.size();i++){
                if(shenCode.equals(areasShi.get(i).getParent())){
                    shiCode = areasShi.get(i).getCode();
                }
            }
        }
        //areasShi.get(0).getCode();
        //获取区的code
        String qu = aa[2];
        List<Area> areasQu = areaRepository.findByLevelAndNameLike(3,qu);
        if(areasQu.size()==0){
            qu = qu.substring(0,qu.length()-1);
            areasQu = areaRepository.findByLevelAndNameLike(3,qu);
            if(areasQu.size()>=1){
                for(int i=0;i<areasQu.size();i++){
                    if(shiCode.equals(areasQu.get(i).getParent())){
                        quCode = areasQu.get(i).getCode();
                    }
                }
            }
        }else if(areasQu.size()>=1){
            for(int i=0;i<areasQu.size();i++){
                if(shiCode.equals(areasQu.get(i).getParent())){
                    quCode = areasQu.get(i).getCode();
                }
            }
        }
        //获取街道的code
        String jiedao = aa[3];
        List<Area> areasJiedao = areaRepository.findByLevelAndNameLike(4,jiedao);
        if(areasJiedao.size()==0){
            jiedao = jiedao.substring(0,jiedao.length()-2);
            areasJiedao = areaRepository.findByLevelAndNameLike(4,jiedao);
            if(areasJiedao.size()>=1){
                for(int i=0;i<areasJiedao.size();i++){
                    if(quCode.equals(areasJiedao.get(i).getParent())){
                        jiedaoCode = areasJiedao.get(i).getCode();
                    }
                }
            }
        }else if(areasJiedao.size()>=1){
            for(int i=0;i<areasJiedao.size();i++){
                if(quCode.equals(areasJiedao.get(i).getParent())){
                    jiedaoCode = areasJiedao.get(i).getCode();
                }
            }
        }
        CustomerAddr customerAddr = new CustomerAddr();
        customerAddr.setCustomerId(customerId);
        customerAddr.setAddress(addr);//详细地址
        customerAddr.setProvince(shenCode);//地区表中省份的ID
        customerAddr.setCity(shiCode);//地区表中城市的ID
        customerAddr.setDistrict(quCode);//地区表中的区ID
        customerAddr.setStreet(jiedaoCode);//地区表中的街道ID
        customerAddr.setModifiedTime(new Date());
        customerAddr.setZip(zip);
        customerAddr.setIsDefault(0);//是否是默认地址
        return customerAddr;
    }

    @Override
    public String changeReceiveAddress(Integer customerId,Integer customerAddrId,String customerName,String mobilePhone,String address,String zip) {
        String[] aa = address.split(" ");
        if(aa.length==5) {
            CustomerAddr customerAddr = getCustomerAddr(customerId, address, zip);
            customerAddr.setCustomerAddrId(customerAddrId);
            customerAddrRepository.save(customerAddr);
            //分析下真实姓名和手机号有没有变
            CustomerInf inf = customerInfRepository.findByCustomerId(customerId).get(0);
            if(!customerName.equals(inf.getCustomerName()) || !mobilePhone.equals(inf.getMobilePhone())){
                inf.setCustomerName(customerName);
                inf.setMobilePhone(mobilePhone);
                inf.setModifiedTime(new Date());
                customerInfRepository.save(inf);
            }
            return "0000";
        }else{
            return "0001";
        }
    }
}
