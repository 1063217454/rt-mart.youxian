package com.order.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：订单主表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 21:17:18
 */
@Entity
@Data
public class OrderMaster {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer orderId;//订单ID
    private BigInteger orderSn;//订单编号
    private Integer customerId;//下单人ID
    private String shippingUser;//收货人姓名
    private int province;//省
    private int city;//市
    private int district;//区
    private String address;//地址
    private Integer paymentMethod;//支付方式：1现金，2余额，3网银，4支付宝，5微信
    private BigDecimal orderMoney;//订单金额
    private BigDecimal districtMoney;//优惠金额
    private BigDecimal shippingMoney;//运费金额
    private BigDecimal paymentMoney;//支付金额
    private String shippingCompName;//快递公司名称
    private String shippingSn;//快递单号
    private Date createTime;//下单时间
    private Date shippingTime;//发货时间
    private Date payTime;//支付时间
    private Date receiveTime;//收货时间
    private Integer orderStatus;//订单状态
    private Integer orderPoint;//订单积分
    private String invoiceTime;//发票抬头
    private Date modifiedTime;//最后修改时间

}