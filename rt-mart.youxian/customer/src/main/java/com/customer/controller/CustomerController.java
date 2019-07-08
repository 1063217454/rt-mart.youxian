package com.customer.controller;

import com.customer.VO.*;
import com.customer.model.CustomerBalanceLog;
import com.customer.model.CustomerInf;
import com.customer.model.CustomerLogin;
import com.customer.server.CustomerService;
import com.customer.util.CustomerLoginVOUtil;
import com.customer.util.FastDFSUtil;
import com.customer.util.ResultVOUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    FastDFSUtil fastDFSUtil;

    /*
    //收费，不用
    @Autowired
    OSSClientUtil clientUtil;*/

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
     * 2、用户登录 ( + 往登录日志表写数据)
     * @param phone
     * @param pwd
     * @return
     */
    @ApiOperation(value = "用户登录",notes = "用户登录")
    @ApiImplicitParams({@ApiImplicitParam(name="phone",value = "用户名(电话号码)",paramType = "path",dataType = "String"),
            @ApiImplicitParam(name="pwd",value = "密码（MD5加密）",paramType = "path",dataType = "String")})
    @PostMapping("/login")
    public CustomerLoginResultVO<CustomerLoginVO> login(@RequestParam(name = "phone") String phone, @RequestParam(name="pwd") String pwd, HttpServletRequest request){
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
     * 3、修改用户真实姓名
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
     * 4、修改用户密码
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


    /**
     * 5、上传用户头像
     * @param customerId
     * @param file
     * @return
     */
    @ApiOperation(value = "用户上传头像",notes = "用户上传头像")
    @ApiImplicitParams({@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer"),
                        @ApiImplicitParam(name="file",value = "图片",paramType = "file",dataType = "MultipartFile")})
    @PostMapping("/modifyHeadPic")
    public Map<String,Object> modifyHeadPic(@RequestHeader("customerId") Integer customerId,
                                            @RequestParam("file") MultipartFile file){
        Map<String,Object> value = new HashMap<String,Object>();
        File f = null;
        System.out.println(file.getSize());
        if(file == null || file.getSize()<=0){
            value.put("headPath","");
            value.put("nessage","图片上传失败");
            value.put("status","0001");
        }else{
            try {//MultipartFile转file
                f = File.createTempFile("temp",".jpg");
                f.deleteOnExit();
                file.transferTo(f);
            } catch (Exception e){
                e.printStackTrace();
            }
            long fileLength = f.length();
            String imgUrl = "";
            try {
                imgUrl = fastDFSUtil.uploadFile(f,file.getOriginalFilename(),fileLength);
            }catch (Exception e){
                e.printStackTrace();
            }
            String message = "上传成功";
            if(!"".equals(imgUrl)){//把这个url存到customerInf表
                CustomerInf customerInf = new CustomerInf();
                customerInf.setCustomerId(customerId);
                customerInf.setHeadPicUrl(imgUrl);
                String code = customerService.modifyHeadPic(customerInf);
                if("0001".equals(code)){
                    message = message + ",更新失败";
                }
            }
            System.out.println("imgUrl="+imgUrl);
            value.put("headPath",imgUrl);
            value.put("message",message);
            value.put("status","0000");
        }
        return value;
    }

    /**
     * 6、根据用户ID查询用户信息
     * @param customerId
     */
    @ApiOperation(value = "根据用户ID查询用户信息",notes = "根据用户ID查询用户信息")
    @ApiImplicitParams(@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer"))
    @GetMapping("/getCustomerById")
    public Map<String,Object> getCustomerById(@RequestHeader("customerId") Integer customerId){
        Map<String,Object> value = new HashMap<String,Object>();
        CustomerLoginAndInfVO vo = customerService.getCustomerById(customerId);
        if(vo==null){
            value.put("result","");
            value.put("message","查询失败");
            value.put("status","0001");
        }else{
            value.put("result",vo);
            value.put("message","查询成功");
            value.put("status","0000");
        }
        return value;
    }

    /**
     * 7、收货地址列表
     * @param customerId
     * @return
     */
    @ApiOperation(value = "收货地址列表",notes = "收货地址列表")
    @ApiImplicitParams({@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer")})
    @GetMapping("/receiveAddressList")
    public Map<String,Object> receiveAddressList(@RequestHeader(name="customerId") Integer customerId){
        Map<String,Object> map = new HashMap<String,Object>();
        List<CustomerAddrAndInfVO> vos = customerService.receiveAddressList(customerId);
        if(vos.size()>0){
            map.put("result",vos);
            map.put("message","查询成功");
            map.put("status","0000");
        }else{
            map.put("result",vos);
            map.put("message","查询失败，未创建过收货地址");
            map.put("status","0001");
        }
        return map;
    }

    /**
     * 8、新增收货地址
     * @param customerId
     * @param customerName
     * @param mobilePhone
     * @param address
     * @param zip
     * @return
     */
    @ApiOperation(value = "新增收货地址",notes = "新增收货地址")
    @ApiImplicitParams({@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer"),
                        @ApiImplicitParam(name="customerName",value = "用户真实姓名",paramType = "path",dataType = "String"),
                        @ApiImplicitParam(name="mobilePhone",value = "手机号",paramType = "path",dataType = "String"),
                        @ApiImplicitParam(name="address",value = "地址",paramType = "path",dataType = "String"),
                        @ApiImplicitParam(name="zip",value = "邮编",paramType = "path",dataType = "String")})
    @PostMapping("/addReceiveAddress")
    public Map<String,Object> addReceiveAddress(@RequestHeader(name="customerId") Integer customerId,
                                                @RequestParam(name = "customerName") String customerName,
                                                @RequestParam(name = "mobilePhone") String mobilePhone,
                                                @RequestParam(name = "address") String address,
                                                @RequestParam(name = "zip") String zip){
        Map<String,Object> map = new HashMap<String, Object>();
        String code = customerService.addReceiveAddress(customerId,customerName,mobilePhone,address,zip);
        if("0000".equals(code)){
            map.put("message","添加成功");
            map.put("status",code);
        }else if("0001".equals(code)){
            map.put("message","添加失败，收货地址信息异常");
            map.put("status",code);
        }else{
            map.put("message","添加失败，更新Inf表里的真实姓名和手机号码信息异常");
            map.put("status",code);
        }

        return map;
    }

    /**
     * 9、设置默认收货地址
     * @param customerId
     * @param customerAddrId
     * @return
     */
    @ApiOperation(value = "设置默认收货地址",notes = "设置默认收货地址")
    @ApiImplicitParams({@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer"),
                        @ApiImplicitParam(name="customerAddrId",value = "收货地址表ID",paramType = "path",dataType = "Integer")})
    @PostMapping("/setDefaultReceiveAddress")
    public Map<String,Object> setDefaultReceiveAddress(@RequestHeader(name="customerId") Integer customerId,
                                                       @RequestParam(name = "customerAddrId") Integer customerAddrId){
        Map<String,Object> map = new HashMap<String, Object>();
        String code = customerService.setDefaultReceiveAddress(customerId,customerAddrId);
        if("0000".equals(code)){
            map.put("message","设置成功");
            map.put("status","0000");
        }else{
            map.put("message","设置失败");
            map.put("status","0001");
        }
        return map;
    }

    /**
     * 10、修改收货信息
     */
    @ApiOperation(value = "修改收货信息",notes = "修改收货信息")
    @ApiImplicitParams({@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer"),
                        @ApiImplicitParam(name="customerAddrId",value = "收货地址表ID",paramType = "path",dataType = "Integer"),
                        @ApiImplicitParam(name="customerName",value = "真实xm",paramType = "path",dataType = "String"),
                        @ApiImplicitParam(name="mobilePhone",value = "手机号",paramType = "path",dataType = "String"),
                        @ApiImplicitParam(name="address",value = "收货地址 客户端将地址的省 市 县 详细地址 以空格间隔 拼接成字符串传给后台",paramType = "path",dataType = "String"),
                        @ApiImplicitParam(name="zip",value = "邮编",paramType = "path",dataType = "String")})
    @PutMapping("/changeReceiveAddress")
    public  Map<String,Object> changeReceiveAddress(@RequestHeader(name="customerId") Integer customerId,
                                     @RequestParam(name = "customerAddrId") Integer customerAddrId,
                                     @RequestParam(name = "customerName") String customerName,
                                     @RequestParam(name = "mobilePhone") String mobilePhone,
                                     @RequestParam(name = "address") String address,
                                     @RequestParam(name = "zip") String zip){
        Map<String,Object> map = new HashMap<String, Object>();
        String code = customerService.changeReceiveAddress(customerId,customerAddrId,customerName,mobilePhone,address,zip);
        if("0000".equals(code)){
            map.put("message","修改成功");
            map.put("status",code);
        }else{
            map.put("message","修改失败");
            map.put("status",code);
        }
        return map;
    }

    /**
     * 11、查询用户钱包
     * @param customerId
     * @param page
     * @param count
     * @return
     */
    @ApiOperation(value = "查询用户钱包",notes = "查询用户钱包")
    @ApiImplicitParams({@ApiImplicitParam(name="customerId",value = "用户id",paramType = "header",dataType = "Integer"),
                        @ApiImplicitParam(name="page",value = "页数（第几页）",paramType = "path",dataType = "Integer"),
                        @ApiImplicitParam(name="count",value = "每页的数据条数",paramType = "path",dataType = "Integer")})
    @GetMapping("/findCustomerWallet")
    public  Map<String,Object> findCustomerWallet(@RequestHeader(name="customerId") Integer customerId,
                                   @RequestParam(name="page") Integer page,
                                   @RequestParam(name="count") Integer count){
        Map<String,Object> map1 = new HashMap<String,Object>();
        Map<String,Object> map = customerService.findCustomerWallet(customerId,page,count);
        List<CustomerBalanceLog> customerBalanceLogs = (List<CustomerBalanceLog>)map.get("detailList");
        if(customerBalanceLogs.size()==0){
            map1.put("result",map);
            map1.put("message","查询失败");
            map1.put("status","0001");
        }else{
            map1.put("result",map);
            map1.put("message","查询成功");
            map1.put("status","0000");
        }
        return map1;
    }


    /*
     * 图片上传
     * 可行，但收费，不用
     */
   /* @PostMapping("/imgUpload")
    public Map<String,Object> imgUpload(@RequestParam("file") MultipartFile file) {
        Map<String,Object> value = new HashMap<String,Object>();
        if(file == null || file.getSize()<=0){
            value.put("success",false);
            value.put("errorCode",200);
            value.put("errorMsg","图片上传失败");
        }else{
            String name = clientUtil.uploadImg2Oss(file);
            String imgUrl = clientUtil.getImgUrl(name);
            System.out.println("imgUrl="+imgUrl);
            value.put("success",true);
            value.put("errorCode",0);
            value.put("errorMsg","");
            value.put("data",imgUrl);
        }
        return value;
    }*/

}
