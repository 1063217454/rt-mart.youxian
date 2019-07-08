package com.order.service.impl;

import com.order.VO.OrderDetailVO;
import com.order.VO.OrderMasterVO;
import com.order.model.OrderDetail;
import com.order.model.OrderMaster;
import com.order.repository.OrderDetailRepository;
import com.order.repository.OrderMasterRepository;
import com.order.service.OrderService;
import com.order.util.HttpClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    private String getTimeCode(Date date){
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(date);
        System.out.println(d);
        String nian = d.substring(0,4);
        String yue = d.substring(5,7);
        String ri = d.substring(8,10);
        String shi = d.substring(11,13);
        String fen = d.substring(14,16);
        String miao = d.substring(17,19);
        return  nian+yue+ri+shi+fen+miao;
    }

    @Override
    public Map<String, Object> createOrder(Integer customerId, String orderinfo, BigDecimal totalPrice,String address,String shippingUser) {
        Map<String, Object> map = new HashMap<>();
        //先存orderMaster
        Date date = new Date();
        String orderSn = getTimeCode(date) + customerId.toString();
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setAddress(address);
        orderMaster.setCreateTime(date);
        orderMaster.setCustomerId(customerId);
        orderMaster.setModifiedTime(date);
        orderMaster.setOrderMoney(totalPrice);
        orderMaster.setOrderPoint(Integer.valueOf(totalPrice.toString()));
        orderMaster.setOrderSn(orderSn);//订单编号  年月日 时分秒（8）+customerID
        orderMaster.setPaymentMoney(totalPrice);
        orderMaster.setPayTime(date);
        orderMaster.setShippingUser(shippingUser);//收货人姓名
        orderMaster.setOrderStatus(1);
        orderMasterRepository.save(orderMaster);
        //还有OrderDetail  orderinfo [{"productId":3,"amount":1},{"productId":5,"amount":1}]
        JSONArray array = JSONArray.fromObject(orderinfo);
        String code = "0000";
        if(array.size()>0){
            for (int i = 0; i < array.size(); i++) {
                //转
                JSONObject O = JSONObject.fromObject(array.get(i));
                String productId = O.get("productId").toString();
                String amount = O.get("amount").toString();
                //调用product项目的接口
                Map<String,String> headerMap = new HashMap<>();
                headerMap.put("customerId",customerId.toString());
                String url = "http://localhost:8091/product/findCommodityDetailsById?productId="+productId;
                try {
                    String result = httpClientUtil.sendGetData(url,headerMap);
                    System.out.println("result==="+result);
                    if(result.length()>0){
                        OrderDetail od = new OrderDetail();
                        //转换
                        JSONObject Object = JSONObject.fromObject(result);
                        //暂时提供的接口得不到这个数据 od.setAverageCost(Object.getJSONObject("result").get("price").toString());
                        //暂时没有这个字段的信息  od.setFeeMoney();
                        Integer orderId = 0;
                        OrderMaster orderMaster1 = orderMasterRepository.findByOrderSn(orderSn);
                        if(orderMaster1!=null){
                            orderId = orderMaster1.getOrderId();
                        }
                        od.setModifiedTime(date);
                        od.setOrderId(orderId);
                        od.setProductCnt(Integer.valueOf(amount));
                        od.setProductId(Integer.valueOf(productId));
                        od.setProductName(Object.getJSONObject("result").get("productName").toString());
                        od.setProductPrice(new BigDecimal(Object.getJSONObject("result").get("price").toString()));
                        od.setWeight(Float.valueOf(Object.getJSONObject("result").get("weight").toString()));
                        od.setWId(1);
                        orderDetailRepository.save(od);
                    }else{
                        code = "0001";

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if("0000".equals(code)){
                map.put("message","创建订单成功");
                map.put("status","0000");
            }else{
                map.put("message","创建订单失败，未能查询到商品信息");
                map.put("status","0001");
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> pay(Integer customerId, String orderSn, Integer payType) {
        Map<String, Object> map = new HashMap<>();
        int num = orderMasterRepository.updateOrderMasterByorderSn(payType,orderSn);
        if(num>0){
            map.put("message","支付成功");
            map.put("status","0000");
        }else{
            map.put("message","支付失败");
            map.put("status","0001");
        }
        return map;
    }

    @Override
    public Map<String, Object> findOrderListByStatus(Integer customerId, Integer status, Integer page, Integer count) {
        Map<String, Object> map = new HashMap<>();
        List<OrderMasterVO> orderMasterVOS = new ArrayList<>();
        //分页查询
        Pageable pageable = PageRequest.of(page - 1, count);//从0开始，所以要-1
        List<OrderMaster> orderMasters = new ArrayList<>();
        if(status==0){
            Page<OrderMaster> page1 = orderMasterRepository.findByCustomerIdPageable(customerId,pageable);
            orderMasters = page1.getContent();
        }else{
            Page<OrderMaster> page1 = orderMasterRepository.findByCustomerIdAndOrderStatusPageable(customerId,status,pageable);
             orderMasters = page1.getContent();
        }
        System.out.println("size=" + orderMasters.size());
        if (orderMasters!=null && orderMasters.size()>0){
            String message = "";
            for (int i=0;i<orderMasters.size();i++){
                OrderMaster om = orderMasters.get(i);
                List<OrderDetailVO> orderDetailVOS = new ArrayList<>();
                List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(om.getOrderId());
                if(orderDetails!=null && orderDetails.size()>0){
                    for(int j=0;j<orderDetails.size();j++){
                        OrderDetail od = orderDetails.get(j);
                        //调接口
                        //调用product项目的接口1 CommentStatus
                        Integer commentStatus = null;
                        Map<String,String> headerMap = new HashMap<>();
                        String url = "http://localhost:8091/product/CommodityCommentList?productId="+
                                od.getProductId()+"&page=1&count=1";
                        //调接口
                        //调用product项目的接口1 ProductPic
                        String productPic = "";
                        String result1 = "";
                        Map<String,String> headerMap1 = new HashMap<>();
                        headerMap1.put("customerId",om.getCustomerId().toString());
                        String url1 = "http://localhost:8091/product/findCommodityDetailsById?productId="+
                                od.getProductId();
                        try {
                            result1 = httpClientUtil.sendGetData(url1,headerMap1);
                            String result = httpClientUtil.sendGetData(url,headerMap);
                            System.out.println("result==="+result);
                            JSONObject jsonObject1 = JSONObject.fromObject(result);
                            JSONArray array = jsonObject1.getJSONArray("result");
                            JSONObject jsonObject = JSONObject.fromObject(result1);
                            productPic = jsonObject.getJSONObject("result").get("picUrl").toString();
                            if(array.size()>0){
                                commentStatus=1;
                            }else{
                                commentStatus=0;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        OrderDetailVO orderDetailVO = new OrderDetailVO();
                        orderDetailVO.setCommentStatus(commentStatus);
                        orderDetailVO.setOrderDetailId(od.getOrderDetailId());
                        orderDetailVO.setProductCnt(od.getProductCnt());
                        orderDetailVO.setProductId(od.getProductId());
                        orderDetailVO.setProductName(od.getProductName());
                        orderDetailVO.setProductPic(productPic);
                        orderDetailVO.setProductPrice(od.getProductPrice());
                        orderDetailVOS.add(orderDetailVO);
                    }
                }else{
                    message+="订单详情表异常，订单编号："+om.getOrderSn()+"\n";
                }
                OrderMasterVO orderMasterVO = new OrderMasterVO();
                orderMasterVO.setCreateTime(om.getCreateTime());
                orderMasterVO.setCustomerId(om.getCustomerId());
                orderMasterVO.setOrderDetailVOS(orderDetailVOS);
                orderMasterVO.setOrderSn(om.getOrderSn());
                orderMasterVO.setOrderStatus(om.getOrderStatus());
                orderMasterVO.setPaymentMethod(om.getPaymentMethod());
                orderMasterVO.setPaymentMoney(om.getPaymentMoney());
                orderMasterVO.setShippingCompName(om.getShippingCompName());
                orderMasterVO.setShippingSn(om.getShippingSn());
                orderMasterVOS.add(orderMasterVO);
            }
            if("".equals(message)){
                map.put("resault",orderMasterVOS);
                map.put("message","查询成功");
                map.put("status","0000");
            }else{
                map.put("resault",orderMasterVOS);
                map.put("message","查询失败,"+message);
                map.put("status","0001");
            }

        }else{
            map.put("resault",orderMasterVOS);
            map.put("message","查询失败");
            map.put("status","0002");
        }
        return map;
    }



}
