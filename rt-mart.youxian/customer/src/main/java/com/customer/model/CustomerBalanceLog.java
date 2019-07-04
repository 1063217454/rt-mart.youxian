package com.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 此类由MySQLToBean工具自动生成
 * 备注(数据表的comment字段)：用户余额变动表
 *
 * @author childlikeman@gmail.com,http://t.qq.com/lostpig
 * @since 2019-06-27 20:41:54
 */
@Entity
@Data
public class CustomerBalanceLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键自增
    private Integer balanceId;//余额日志ID
    private Integer customerId;//用户ID
    private Integer source;//记录来源：1订单，2退货单, 3充值
    private Integer sourceSn;//相关单据ID
    private Date createTime;//记录生成时间
    private BigDecimal amount;//用户消费金额

}